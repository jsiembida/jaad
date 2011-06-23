/*
 * Copyright (C) 2010 in-somnia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.jaad.aac.ps2;

import java.util.Arrays;
import net.sourceforge.jaad.aac.AACException;
import net.sourceforge.jaad.aac.syntax.BitStream;

public class PS implements PSConstants, PSTables, HuffmanTables {

	//generated tables
	private final float[][][] HA, HB;
	private final float[][] PHI_FRACT_20, PHI_FRACT_34;
	private final float[][][] Q_FRACT_ALLPASS_20, Q_FRACT_ALLPASS_34;
	//header
	private boolean headerEnabled;
	private final PSHeader header;
	//bitstream variables
	private boolean frameClass;
	private int envCount, envCountPrev;
	private final int[] borderPositions;
	//pars
	private final int[][] iidPars, iccPars, ipdPars, opdPars;
	private final int[] ipdPrev, opdPrev;
	//dequantized values
	//private final float[][] iid, icc, ipd, opd;
	//working buffer
	private final float[][][] lBuf, rBuf;
	//buffers for decorrelation in z-domain
	private final float[] peakDecayNrg, smoothNrg, smoothPeakDecayDiffNrg;
	private final float[][][] delay;
	private final float[][][][] apDelay;
	//buffers for stereo processing
	private final float[][][] H11, H12, H21, H22;

	public PS() {
		HA = new float[46][8][4];
		HB = new float[46][8][4];
		TableGenerator.generateMixingTables(HA, HB);
		PHI_FRACT_20 = new float[30][2];
		Q_FRACT_ALLPASS_20 = new float[30][3][2];
		TableGenerator.generateFractTables20(PHI_FRACT_20, Q_FRACT_ALLPASS_20);
		PHI_FRACT_34 = new float[50][2];
		Q_FRACT_ALLPASS_34 = new float[50][3][2];
		TableGenerator.generateFractTables34(PHI_FRACT_34, Q_FRACT_ALLPASS_34);

		headerEnabled = false;
		header = new PSHeader();

		borderPositions = new int[MAX_ENVELOPES];

		iidPars = new int[MAX_ENVELOPES][MAX_IID_ICC_PARS];
		iccPars = new int[MAX_ENVELOPES][MAX_IID_ICC_PARS];
		ipdPars = new int[MAX_ENVELOPES][MAX_IPD_OPD_PARS];
		opdPars = new int[MAX_ENVELOPES][MAX_IPD_OPD_PARS];
		ipdPrev = new int[MAX_IPD_OPD_PARS];
		opdPrev = new int[MAX_IPD_OPD_PARS];

		/*iid = new float[MAX_ENVELOPES][MAX_IID_ICC_PARS];
		icc = new float[MAX_ENVELOPES][MAX_IID_ICC_PARS];
		ipd = new float[MAX_ENVELOPES][MAX_IID_ICC_PARS];
		opd = new float[MAX_ENVELOPES][MAX_IID_ICC_PARS];*/

		lBuf = new float[91][32][2];
		rBuf = new float[91][32][2];
		peakDecayNrg = new float[MAX_IID_ICC_PARS];
		smoothNrg = new float[MAX_IID_ICC_PARS];
		smoothPeakDecayDiffNrg = new float[MAX_IID_ICC_PARS];
		delay = new float[91][32+MAX_DELAY][2];
		apDelay = new float[50][ALLPASS_LINKS][32+MAX_DELAY][2];

		H11 = new float[2][MAX_ENVELOPES+1][MAX_IID_ICC_PARS];
		H12 = new float[2][MAX_ENVELOPES+1][MAX_IID_ICC_PARS];
		H21 = new float[2][MAX_ENVELOPES+1][MAX_IID_ICC_PARS];
		H22 = new float[2][MAX_ENVELOPES+1][MAX_IID_ICC_PARS];
	}

	/*========================= decoding =========================*/
	public void decode(BitStream in) throws AACException {
		header.startNewFrame();
		if(headerEnabled = in.readBool()) header.decode(in);

		frameClass = in.readBool();
		//envelopes (table 8.29)
		final int envIdx = in.readBits(2);
		envCountPrev = envCount;
		envCount = envIdx+(frameClass ? 1 : 0);
		if(envIdx==3&&!frameClass) envCount++;
		//if envCount==0: no new parameters, use old ones
		if(envCount==0) envCount = envCountPrev;

		//border positions
		int e;
		if(frameClass) {
			for(e = 0; e<envCount; e++) {
				borderPositions[e] = in.readBits(5);
			}
		}

		int len;
		boolean dt;
		int[][] table;
		//iid
		if(header.isIIDEnabled()) {
			len = header.getIIDPars();
			final boolean fine = header.useIIDQuantFine();
			final float[] quant = fine ? IID_QUANT_FINE : IID_QUANT_DEFAULT;
			for(e = 0; e<envCount; e++) {
				dt = in.readBool();
				table = dt ? (fine ? HUFF_IID_FINE_DT : HUFF_IID_DEFAULT_DT)
						: (fine ? HUFF_IID_FINE_DF : HUFF_IID_DEFAULT_DF);
				decodePars(in, table, iidPars, e, len, dt, false);
				//dequant(iidPars[e], iid[e], len, quant);
			}
		}

		//icc
		if(header.isICCEnabled()) {
			len = header.getICCPars();
			for(e = 0; e<envCount; e++) {
				dt = in.readBool();
				table = dt ? HUFF_ICC_DT : HUFF_ICC_DF;
				decodePars(in, table, iccPars, e, len, dt, false);
				//dequant(iccPars[e], icc[e], len, ICC_QUANT);
			}
		}

		//extension
		if(header.isExtEnabled()) {
			int left = in.readBits(4);
			if(left==15) left += in.readBits(8);
			left *= 8;

			int id;
			while(left>7) {
				id = in.readBits(2);
				left -= 2;
				left -= decodeExtension(in, id);
			}

			in.skipBits(left);
		}
	}

	private void decodePars(BitStream in, int[][] table, int[][] pars, int env, int len, boolean dt, boolean mod) throws AACException {
		//huffman delta decoding
		if(dt) {
			final int prev = (env>0) ? env-1 : envCountPrev-1;
			for(int i = 0; i<len; i++) {
				pars[env][i] = pars[prev][i]+decodeHuffman(in, table);
				if(mod) pars[env][i] &= 7;
			}
		}
		else {
			pars[env][0] = decodeHuffman(in, table);
			for(int i = 1; i<len; i++) {
				pars[env][i] = pars[env][i-1]+decodeHuffman(in, table);
				if(mod) pars[env][i] &= 7;
			}
		}
	}

	private int decodeHuffman(BitStream in, int[][] table) throws AACException {
		int off = 0;
		int len = table[off][0];
		int cw = in.readBits(len);
		int j;
		while(cw!=table[off][1]) {
			off++;
			j = table[off][0]-len;
			len = table[off][0];
			cw <<= j;
			cw |= in.readBits(j);
		}
		return table[off][2];
	}

	private int decodeExtension(BitStream in, int id) throws AACException {
		final int start = in.getPosition();

		if(id==0) {
			//ipdopd
			final boolean b = in.readBool();
			header.setIPDOPDEnabled(b);
			if(b) {
				final int len = header.getIPDOPDPars();
				boolean dt;
				int[][] table;

				for(int e = 0; e<envCount; e++) {
					dt = in.readBool();
					table = dt ? HUFF_IPD_DT : HUFF_IPD_DF;
					decodePars(in, table, ipdPars, e, len, dt, true);
					//dequant(ipdPars[e], ipd[e], len, dt ? IPD_OPD_QUANT : ICC_QUANT);

					dt = in.readBool();
					table = dt ? HUFF_OPD_DT : HUFF_OPD_DF;
					decodePars(in, table, opdPars, e, len, dt, true);
					//dequant(opdPars[e], opd[e], len, dt ? IPD_OPD_QUANT : ICC_QUANT);
				}
			}
			in.skipBit(); //reserved
		}

		return in.getPosition()-start;
	}

	/*========================= dequantization=========================*/
	/*private void dequant(int[] pars, float[] vals, int len, float[] table) {
	for(int i = 0; i<len; i++) {
	vals[i] = table[pars[i]];
	}
	}*/

	/*========================= processing =========================*/
	public boolean hasHeader() {
		return headerEnabled;
	}

	//in: 64 x 38 complex from SBR, left/right: 2048 output time samples
	public void process(float[][][] in, float[] left, float[] right) {
		//1. hybrid analysis (in -> lBuf)
		AnalysisFilterbank.process(in, lBuf, header.use34Bands(false));

		//2. decorrelation (lBuf -> rBuf)
		decorrelate();

		//3. stereo processing
		performStereoProcessing();

		//4. hybrid synthesis
	}

	private void decorrelate() {
		final boolean use34 = header.use34Bands(false);

		//reset if necessary
		if(header.use34Bands(true)!=use34) {
			Arrays.fill(peakDecayNrg, 0);
			Arrays.fill(smoothNrg, 0);
			Arrays.fill(smoothPeakDecayDiffNrg, 0);
			for(int i = 0; i<delay.length; i++) {
				for(int j = 0; j<delay[i].length; j++) {
					delay[i][j][0] = 0;
					delay[i][j][1] = 0;
				}
			}
		}

		final int mode = header.getBandMode();
		final int[] map = use34 ? K_TO_BK_34 : K_TO_BK_20;
		int k, n, m, b;

		//calculate transients
		final int parBands = PAR_BANDS[mode];
		final float[][] power = new float[parBands][32];
		for(k = 0; k<32; k++) {
			Arrays.fill(power[k], 0);
		}
		for(k = 0; k<parBands; k++) {
			for(n = 0; n<32; n++) {
				b = map[k];
				power[b][n] += lBuf[k][n][0]*lBuf[k][n][0]+lBuf[k][n][1]*lBuf[k][n][1];
			}
		}

		//perform transient detection
		final float[][] gTransientRatio = new float[parBands][32];
		float tmp;
		for(k = 0; k<parBands; k++) {
			peakDecayNrg[k] = power[k][0];
			for(n = 0; n<32; n++) {
				peakDecayNrg[k] *= PEAK_DECAY_FACTOR;
				peakDecayNrg[k] = Math.max(peakDecayNrg[k], power[k][n]);
				smoothNrg[k] += A_SMOOTH*(power[k][n]-smoothNrg[k]);
				smoothPeakDecayDiffNrg[k] += A_SMOOTH*(peakDecayNrg[k]-power[k][n]-smoothPeakDecayDiffNrg[k]);
				tmp = GAMMA*smoothPeakDecayDiffNrg[k];
				gTransientRatio[k][n] = (tmp>smoothNrg[k]) ? (smoothNrg[k]/tmp) : 1.0f;
			}
		}
		final float[][] transientGain = new float[parBands][32];
		for(k = 0; k<parBands; k++) {
			for(n = 0; n<32; n++) {
				transientGain[k][n] = gTransientRatio[map[k]][n];
			}
		}

		//calculate transfer function and apply transient reduction
		final int allpassBands = ALLPASS_BANDS[mode];

		final float[][] phiFract = use34 ? PHI_FRACT_34 : PHI_FRACT_20;
		final float[][][] qFract = use34 ? Q_FRACT_ALLPASS_34 : Q_FRACT_ALLPASS_20;
		final float[][][] H = new float[allpassBands][32][2];

		final float[] ag = new float[ALLPASS_LINKS];
		float gDecaySlope, re, im;
		for(k = 0; k<allpassBands; k++) {
			b = map[k];
			gDecaySlope = (k>DECAY_CUTOFF[mode]) ? Math.max(0, 1-DECAY_SLOPE*(k-DECAY_CUTOFF[mode])) : 1;
			for(m = 0; m<ALLPASS_LINKS; m++) {
				for(n = 0; n<5; n++) {
					apDelay[k][m][n][0] = apDelay[k][m][32][0];
					apDelay[k][m][n][1] = apDelay[k][m][32][1];
				}
				ag[m] = FILTER_COEFFICIENTS[m]*gDecaySlope;
			}

			addNewSamples(k);

			for(n = 0; n<32; n++) {
				re = delay[k][n+MAX_DELAY-2][0]*phiFract[k][0]
						-delay[k][n+MAX_DELAY-2][1]*phiFract[k][1];
				im = delay[k][n+MAX_DELAY-2][0]*phiFract[k][1]
						+delay[k][n+MAX_DELAY-2][1]*phiFract[k][0];
				for(m = 0; m<ALLPASS_LINKS; m++) {
					float a_re = ag[m]*re;
					float a_im = ag[m]*im;
					float link_delay_re = apDelay[k][m][n+5-LINK_DELAY[m]][0];
					float link_delay_im = apDelay[k][m][n+5-LINK_DELAY[m]][1];
					float fractional_delay_re = qFract[k][m][0];
					float fractional_delay_im = qFract[k][m][1];
					apDelay[k][m][n+5][0] = re;
					apDelay[k][m][n+5][1] = im;
					re = link_delay_re*fractional_delay_re-link_delay_im*fractional_delay_im-a_re;
					im = link_delay_re*fractional_delay_im+link_delay_im*fractional_delay_re-a_im;
					apDelay[k][m][n+5][0] += ag[m]*re;
					apDelay[k][m][n+5][1] += ag[m]*im;
				}
				rBuf[k][n][0] = transientGain[b][n]*re;
				rBuf[k][n][1] = transientGain[b][n]*im;
			}
		}
		for(k = allpassBands; k<SHORT_DELAY_BANDS[mode]; k++) {
			addNewSamples(k);
			for(n = 0; n<32; n++) {
				rBuf[k][n][0] = transientGain[map[k]][n]*delay[k][n+MAX_DELAY-14][0];
				rBuf[k][n][1] = transientGain[map[k]][n]*delay[k][n+MAX_DELAY-14][1];
			}
		}
		for(k = SHORT_DELAY_BANDS[mode]; k<BANDS[mode]; k++) {
			addNewSamples(k);
			for(n = 0; n<32; n++) {
				//H = delay 1
				rBuf[k][n][0] = transientGain[map[k]][n]*delay[k][n+MAX_DELAY-1][0];
				rBuf[k][n][1] = transientGain[map[k]][n]*delay[k][n+MAX_DELAY-1][1];
			}
		}
	}

	//helper method for decorrelation
	private void addNewSamples(int k) {
		//copy previous
		for(int n = 0; n<MAX_DELAY; n++) {
			delay[k][n][0] = delay[k][n+32][0];
			delay[k][n][1] = delay[k][n+32][1];
		}
		//add new
		for(int n = 0; n<32; n++) {
			delay[k][n+MAX_DELAY][0] = lBuf[k][n][0];
			delay[k][n+MAX_DELAY][1] = lBuf[k][n][1];
		}
	}

	private void performStereoProcessing() {
		//remapping
		mapPars(iidPars, header.getIIDPars(), true);
		mapPars(iccPars, header.getICCPars(), true);
		if(header.isIPDOPDEnabled()) {
			final int pars = header.getIPDOPDPars();
			mapPars(ipdPars, pars, true);
			mapPars(opdPars, pars, true);
		}

		System.arraycopy(H11[0][envCountPrev], 0, H11[0][0], 0, 34);
		System.arraycopy(H11[1][envCountPrev], 0, H11[0][0], 0, 34);
		System.arraycopy(H12[0][envCountPrev], 0, H12[0][0], 0, 34);
		System.arraycopy(H12[1][envCountPrev], 0, H12[0][0], 0, 34);
		System.arraycopy(H21[0][envCountPrev], 0, H21[0][0], 0, 34);
		System.arraycopy(H21[1][envCountPrev], 0, H21[0][0], 0, 34);
		System.arraycopy(H22[0][envCountPrev], 0, H22[0][0], 0, 34);
		System.arraycopy(H22[1][envCountPrev], 0, H22[0][0], 0, 34);
		final boolean use34 = header.use34Bands(false), use34Prev = header.use34Bands(true);
		if(use34&&!use34Prev) {
			Utils.map20To34(H11[0][0]);
			Utils.map20To34(H11[1][0]);
			Utils.map20To34(H12[0][0]);
			Utils.map20To34(H12[1][0]);
			Utils.map20To34(H21[0][0]);
			Utils.map20To34(H21[1][0]);
			Utils.map20To34(H22[0][0]);
			Utils.map20To34(H22[1][0]);
			Arrays.fill(ipdPrev, 0);
			Arrays.fill(opdPrev, 0);
		}
		else if(!use34&&use34Prev) {
			Utils.map34To20(H11[0][0]);
			Utils.map34To20(H11[1][0]);
			Utils.map34To20(H12[0][0]);
			Utils.map34To20(H12[1][0]);
			Utils.map34To20(H21[0][0]);
			Utils.map34To20(H21[1][0]);
			Utils.map34To20(H22[0][0]);
			Utils.map34To20(H22[1][0]);
			Arrays.fill(ipdPrev, 0);
			Arrays.fill(opdPrev, 0);
		}

		//mixing
		//TODO...
	}

	private void mapPars(int[][] pars, int len, boolean full) {
		int i;

		if(header.use34Bands(false)) {
			if(len==10) {
				for(i = 0; i<envCount; i++) {
					Utils.map10To34(pars[i], full);
				}
			}
			else if(len==20) {
				for(i = 0; i<envCount; i++) {
					Utils.map20To34(pars[i], full);
				}
			}
		}
		else {
			if(len==10) {
				for(i = 0; i<envCount; i++) {
					Utils.map10To20(pars[i], full);
				}
			}
			else if(len==34) {
				for(i = 0; i<envCount; i++) {
					Utils.map34To20(pars[i], full);
				}
			}
		}
	}
}
