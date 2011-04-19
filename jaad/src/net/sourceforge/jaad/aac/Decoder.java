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
package net.sourceforge.jaad.aac;

import java.io.InputStream;
import net.sourceforge.jaad.aac.syntax.BitStream;
import net.sourceforge.jaad.aac.syntax.Constants;
import net.sourceforge.jaad.aac.syntax.PCE;
import net.sourceforge.jaad.aac.syntax.SyntacticElements;
import net.sourceforge.jaad.aac.filterbank.FilterBank;
import net.sourceforge.jaad.aac.transport.ADIFHeader;
import net.sourceforge.jaad.aac.transport.ADTSFrame;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import net.sourceforge.jaad.aac.syntax.InputBitStream;

/**
 * Main AAC decoder class
 * @author in-somnia
 */
public class Decoder implements Constants {

	static {
		LOGGER.setLevel(Level.CONFIG);
		final ConsoleHandler h = new ConsoleHandler();
		h.setLevel(Level.FINE);
		LOGGER.addHandler(h);
	}
	private final DecoderConfig config;
	private final SyntacticElements syntacticElements;
	private final FilterBank filterBank;
	private BitStream in;
	private ADIFHeader adifHeader;
	private ADTSFrame adtsFrame;

	/**
	 * The methods returns true, if a profile is supported by the decoder.
	 * @param profile an AAC profile
	 * @return true if the specified profile can be decoded
	 * @see Profile#isDecodingSupported()
	 */
	public static boolean canDecode(Profile profile) {
		return profile.isDecodingSupported();
	}

	/**
	 * Initializes the decoder with an InputStream to read from. This
	 * constructor can only be used with a stream containing an ADTS or ADIF
	 * header. This constructor may skip up to the maximum framelength (6144
	 * bytes) to find the next transport header in the stream.
	 * 
	 * After this the <code>decodeFrame(SampleBuffer)</code> method can be used
	 * to decode the frames.
	 * 
	 * @param in an InputStream to read from
	 * @throws AACException if the specified profile is not supported
	 */
	public Decoder(InputStream in) throws AACException {
		config = DecoderConfig.parseTransportHeader(in);
		if(config==null) throw new IllegalArgumentException("no transport header could be found in the stream");

		if(!canDecode(config.getProfile())) throw new AACException("unsupported profile: "+config.getProfile().getDescription());

		syntacticElements = new SyntacticElements(config);
		filterBank = new FilterBank(config.isSmallFrameUsed(), config.getChannelConfiguration().getChannelCount());

		this.in = new InputBitStream(in);

		printLog();
	}

	/**
	 * Initializes the decoder with a MP4 decoder specific info.
	 *
	 * After this the MP4 frames can be passed to the
	 * <code>decodeFrame(byte[], SampleBuffer)</code> method to decode them.
	 * 
	 * @param decoderSpecificInfo a byte array containing the decoder specific info from an MP4 container
	 * @throws AACException if the specified profile is not supported
	 */
	public Decoder(byte[] decoderSpecificInfo) throws AACException {
		config = DecoderConfig.parseMP4DecoderSpecificInfo(decoderSpecificInfo);
		if(config==null) throw new IllegalArgumentException("illegal MP4 decoder specific info");

		if(!canDecode(config.getProfile())) throw new AACException("unsupported profile: "+config.getProfile().getDescription());

		syntacticElements = new SyntacticElements(config);
		filterBank = new FilterBank(config.isSmallFrameUsed(), config.getChannelConfiguration().getChannelCount());

		in = new BitStream();

		printLog();
	}

	private void printLog() {
		LOGGER.log(Level.FINE, "profile: {0}", config.getProfile());
		LOGGER.log(Level.FINE, "sf: {0}", config.getSampleFrequency().getFrequency());
		LOGGER.log(Level.FINE, "channels: {0}", config.getChannelConfiguration().getDescription());
	}

	public DecoderConfig getConfig() {
		return config;
	}

	/**
	 * Decodes one frame of AAC data in frame mode and returns the raw PCM
	 * data.
	 * @param frame the AAC frame
	 * @param buffer a buffer to hold the decoded PCM data
	 * @throws AACException if decoding fails
	 */
	public void decodeFrame(byte[] frame, SampleBuffer buffer) throws AACException {
		if(frame!=null) in.setData(frame);
		decodeFrame(buffer);
	}

	/**
	 * Decodes one frame of AAC data in stream mode and returns the raw PCM
	 * data.
	 * @param buffer a buffer to hold the decoded PCM data
	 * @throws AACException if decoding fails
	 * @return true if a frame could be decoded, false if the stream ended
	 */
	public boolean decodeFrame(SampleBuffer buffer) throws AACException {
		try {
			decode(buffer);
			return true;
		}
		catch(AACException e) {
			if(e.isEndOfStream()) return false;
			else throw e;
		}
	}

	private void decode(SampleBuffer buffer) throws AACException {
		if(ADIFHeader.isPresent(in)) {
			adifHeader = ADIFHeader.readHeader(in);
			final PCE pce = adifHeader.getFirstPCE();
			config.setProfile(pce.getProfile());
			config.setSampleFrequency(pce.getSampleFrequency());
			config.setChannelConfiguration(ChannelConfiguration.forInt(pce.getChannelCount()));
		}
		if(ADTSFrame.isPresent(in)) {
			adtsFrame = ADTSFrame.readFrame(in);
			config.setProfile(adtsFrame.getProfile());
			config.setSampleFrequency(adtsFrame.getSampleFrequency());
			config.setChannelConfiguration(adtsFrame.getChannelConfiguration());
		}

		if(!canDecode(config.getProfile())) throw new AACException("unsupported profile: "+config.getProfile().getDescription());

		syntacticElements.startNewFrame();

		try {
			//1: bitstream parsing and noiseless coding
			syntacticElements.decode(in);
			//2: spectral processing
			syntacticElements.process(filterBank);
			//3: send to output buffer
			syntacticElements.sendToOutput(buffer);
		}
		catch(AACException e) {
			buffer.setData(new byte[0], 0, 0, 0, 0);
			throw e;
		}
		catch(Exception e) {
			buffer.setData(new byte[0], 0, 0, 0, 0);
			throw new AACException(e);
		}
	}
}
