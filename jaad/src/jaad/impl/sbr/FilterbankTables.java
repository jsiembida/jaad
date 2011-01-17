/*
 * Copyright (C 2010 in-somnia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jaad.impl.sbr;

interface FilterbankTables {

	float[][] QMF32_PRE_TWIDDLE = {
		{0.999924701839145f, -0.012271538285720f},
		{0.999322384588350f, -0.036807222941359f},
		{0.998118112900149f, -0.061320736302209f},
		{0.996312612182778f, -0.085797312344440f},
		{0.993906970002356f, -0.110222207293883f},
		{0.990902635427780f, -0.134580708507126f},
		{0.987301418157858f, -0.158858143333861f},
		{0.983105487431216f, -0.183039887955141f},
		{0.978317370719628f, -0.207111376192219f},
		{0.972939952205560f, -0.231058108280671f},
		{0.966976471044852f, -0.254865659604515f},
		{0.960430519415566f, -0.278519689385053f},
		{0.953306040354194f, -0.302005949319228f},
		{0.945607325380521f, -0.325310292162263f},
		{0.937339011912575f, -0.348418680249435f},
		{0.928506080473216f, -0.371317193951838f},
		{0.919113851690058f, -0.393992040061048f},
		{0.909167983090522f, -0.416429560097637f},
		{0.898674465693954f, -0.438616238538528f},
		{0.887639620402854f, -0.460538710958240f},
		{0.876070094195407f, -0.482183772079123f},
		{0.863972856121587f, -0.503538383725718f},
		{0.851355193105265f, -0.524589682678469f},
		{0.838224705554838f, -0.545324988422046f},
		{0.824589302785025f, -0.565731810783613f},
		{0.810457198252595f, -0.585797857456439f},
		{0.795836904608884f, -0.605511041404326f},
		{0.780737228572094f, -0.624859488142386f},
		{0.765167265622459f, -0.643831542889791f},
		{0.749136394523459f, -0.662415777590172f},
		{0.732654271672413f, -0.680600997795453f},
		{0.715730825283819f, -0.698376249408973f}
	};
	float[] QMF_C = {
		0f, -0.00055252865047f,
		-0.00056176925738f, -0.00049475180896f,
		-0.00048752279712f, -0.00048937912498f,
		-0.00050407143497f, -0.00052265642972f,
		-0.00054665656337f, -0.00056778025613f,
		-0.00058709304852f, -0.00061327473938f,
		-0.00063124935319f, -0.00065403333621f,
		-0.00067776907764f, -0.00069416146273f,
		-0.00071577364744f, -0.00072550431222f,
		-0.00074409418541f, -0.00074905980532f,
		-0.0007681371927f, -0.00077248485949f,
		-0.00078343322877f, -0.00077798694927f,
		-0.000780366471f, -0.00078014496257f,
		-0.0007757977331f, -0.00076307935757f,
		-0.00075300014201f, -0.00073193571525f,
		-0.00072153919876f, -0.00069179375372f,
		-0.00066504150893f, -0.00063415949025f,
		-0.0005946118933f, -0.00055645763906f,
		-0.00051455722108f, -0.00046063254803f,
		-0.00040951214522f, -0.00035011758756f,
		-0.00028969811748f, -0.0002098337344f,
		-0.00014463809349f, -6.173344072E-005f,
		1.349497418E-005f, 0.00010943831274f,
		0.00020430170688f, 0.00029495311041f,
		0.0004026540216f, 0.00051073884952f,
		0.00062393761391f, 0.00074580258865f,
		0.00086084433262f, 0.00098859883015f,
		0.00112501551307f, 0.00125778846475f,
		0.00139024948272f, 0.00154432198471f,
		0.00168680832531f, 0.00183482654224f,
		0.00198411407369f, 0.00214615835557f,
		0.00230172547746f, 0.00246256169126f,
		0.00262017586902f, 0.00278704643465f,
		0.00294694477165f, 0.00311254206525f,
		0.00327396134847f, 0.00344188741828f,
		0.00360082681231f, 0.00376039229104f,
		0.00392074323703f, 0.00408197531935f,
		0.0042264269227f, 0.00437307196781f,
		0.00452098527825f, 0.00466064606118f,
		0.00479325608498f, 0.00491376035745f,
		0.00503930226013f, 0.00514073539032f,
		0.00524611661324f, 0.00534716811982f,
		0.00541967759307f, 0.00548760401507f,
		0.00554757145088f, 0.00559380230045f,
		0.00562206432097f, 0.00564551969164f,
		0.00563891995151f, 0.00562661141932f,
		0.0055917128663f, 0.005540436394f,
		0.0054753783077f, 0.0053838975897f,
		0.00527157587272f, 0.00513822754514f,
		0.00498396877629f, 0.004810946906f,
		0.00460395301471f, 0.00438018617447f,
		0.0041251642327f, 0.00384564081246f,
		0.00354012465507f, 0.00320918858098f,
		0.00284467578623f, 0.00245085400321f,
		0.0020274176185f, 0.00157846825768f,
		0.00109023290512f, 0.0005832264248f,
		2.760451905E-005f, -0.00054642808664f,
		-0.00115681355227f, -0.00180394725893f,
		-0.00248267236449f, -0.003193377839f,
		-0.00394011240522f, -0.004722259624f,
		-0.00553372111088f, -0.00637922932685f,
		-0.00726158168517f, -0.00817982333726f,
		-0.00913253296085f, -0.01011502154986f,
		-0.01113155480321f, -0.01218499959508f,
		0.01327182200351f, 0.01439046660792f,
		0.01554055533423f, 0.01673247129989f,
		0.01794333813443f, 0.01918724313698f,
		0.02045317933555f, 0.02174675502535f,
		0.02306801692862f, 0.02441609920285f,
		0.02578758475467f, 0.02718594296329f,
		0.02860721736385f, 0.03005026574279f,
		0.03150176087389f, 0.03297540810337f,
		0.03446209487686f, 0.03596975605542f,
		0.03748128504252f, 0.03900536794745f,
		0.04053491705584f, 0.04206490946367f,
		0.04360975421304f, 0.04514884056413f,
		0.04668430272642f, 0.04821657200672f,
		0.04973857556014f, 0.05125561555216f,
		0.05276307465207f, 0.05424527683589f,
		0.05571736482138f, 0.05716164501299f,
		0.0585915683626f, 0.05998374801761f,
		0.06134551717207f, 0.06268578081172f,
		0.06397158980681f, 0.0652247106438f,
		0.06643675122104f, 0.06760759851228f,
		0.06870438283512f, 0.06976302447127f,
		0.07076287107266f, 0.07170026731102f,
		0.07256825833083f, 0.07336202550803f,
		0.07410036424342f, 0.07474525581194f,
		0.07531373362019f, 0.07580083586584f,
		0.07619924793396f, 0.07649921704119f,
		0.07670934904245f, 0.07681739756964f,
		0.07682300113923f, 0.07672049241746f,
		0.07650507183194f, 0.07617483218536f,
		0.07573057565061f, 0.0751576255287f,
		0.07446643947564f, 0.0736406005762f,
		0.07267746427299f, 0.07158263647903f,
		0.07035330735093f, 0.06896640131951f,
		0.06745250215166f, 0.06576906686508f,
		0.06394448059633f, 0.06196027790387f,
		0.0598166570809f, 0.05751526919867f,
		0.05504600343009f, 0.05240938217366f,
		0.04959786763445f, 0.04663033051701f,
		0.04347687821958f, 0.04014582784127f,
		0.03664181168133f, 0.03295839306691f,
		0.02908240060125f, 0.02503075618909f,
		0.02079970728622f, 0.01637012582228f,
		0.01176238327857f, 0.00696368621617f,
		0.00197656014503f, -0.00320868968304f,
		-0.00857117491366f, -0.01412888273558f,
		-0.01988341292573f, -0.02582272888064f,
		-0.03195312745332f, -0.03827765720822f,
		-0.04478068215856f, -0.05148041767934f,
		-0.05837053268336f, -0.06544098531359f,
		-0.07269433008129f, -0.08013729344279f,
		-0.08775475365593f, -0.09555333528914f,
		-0.10353295311463f, -0.1116826931773f,
		-0.120007798468f, -0.12850028503878f,
		-0.13715517611934f, -0.1459766491187f,
		-0.15496070710605f, -0.16409588556669f,
		-0.17338081721706f, -0.18281725485142f,
		-0.19239667457267f, -0.20212501768103f,
		-0.21197358538056f, -0.22196526964149f,
		-0.23206908706791f, -0.24230168845974f,
		-0.25264803095722f, -0.26310532994603f,
		-0.27366340405625f, -0.28432141891085f,
		-0.29507167170646f, -0.30590985751916f,
		-0.31682789136456f, -0.32781137272105f,
		-0.33887226938665f, -0.3499914122931f,
		0.36115899031355f, 0.37237955463061f,
		0.38363500139043f, 0.39492117615675f,
		0.40623176767625f, 0.41756968968409f,
		0.42891199207373f, 0.44025537543665f,
		0.45159965356824f, 0.46293080852757f,
		0.47424532146115f, 0.48552530911099f,
		0.49677082545707f, 0.50798175000434f,
		0.51912349702391f, 0.53022408956855f,
		0.54125534487322f, 0.55220512585061f,
		0.5630789140137f, 0.57385241316923f,
		0.58454032354679f, 0.59511230862496f,
		0.6055783538918f, 0.61591099320291f,
		0.62612426956055f, 0.63619801077286f,
		0.64612696959461f, 0.65590163024671f,
		0.66551398801627f, 0.67496631901712f,
		0.68423532934598f, 0.69332823767032f,
		0.70223887193539f, 0.71094104263095f,
		0.71944626349561f, 0.72774489002994f,
		0.73582117582769f, 0.74368278636488f,
		0.75131374561237f, 0.75870807608242f,
		0.76586748650939f, 0.77277808813327f,
		0.77942875190216f, 0.7858353120392f,
		0.79197358416424f, 0.797846641377f,
		0.80344857518505f, 0.80876950044491f,
		0.81381912706217f, 0.81857760046468f,
		0.82304198905409f, 0.8272275347336f,
		0.8311038457152f, 0.83469373618402f,
		0.83797173378865f, 0.84095413924722f,
		0.84362382812005f, 0.84598184698206f,
		0.84803157770763f, 0.84978051984268f,
		0.85119715249343f, 0.85230470352147f,
		0.85310209497017f, 0.85357205739107f,
		0.85373856005937f /*max*/, 0.85357205739107f,
		0.85310209497017f, 0.85230470352147f,
		0.85119715249343f, 0.84978051984268f,
		0.84803157770763f, 0.84598184698206f,
		0.84362382812005f, 0.84095413924722f,
		0.83797173378865f, 0.83469373618402f,
		0.8311038457152f, 0.8272275347336f,
		0.82304198905409f, 0.81857760046468f,
		0.81381912706217f, 0.80876950044491f,
		0.80344857518505f, 0.797846641377f,
		0.79197358416424f, 0.7858353120392f,
		0.77942875190216f, 0.77277808813327f,
		0.76586748650939f, 0.75870807608242f,
		0.75131374561237f, 0.74368278636488f,
		0.73582117582769f, 0.72774489002994f,
		0.71944626349561f, 0.71094104263095f,
		0.70223887193539f, 0.69332823767032f,
		0.68423532934598f, 0.67496631901712f,
		0.66551398801627f, 0.65590163024671f,
		0.64612696959461f, 0.63619801077286f,
		0.62612426956055f, 0.61591099320291f,
		0.6055783538918f, 0.59511230862496f,
		0.58454032354679f, 0.57385241316923f,
		0.5630789140137f, 0.55220512585061f,
		0.54125534487322f, 0.53022408956855f,
		0.51912349702391f, 0.50798175000434f,
		0.49677082545707f, 0.48552530911099f,
		0.47424532146115f, 0.46293080852757f,
		0.45159965356824f, 0.44025537543665f,
		0.42891199207373f, 0.41756968968409f,
		0.40623176767625f, 0.39492117615675f,
		0.38363500139043f, 0.37237955463061f,
		-0.36115899031355f, -0.3499914122931f,
		-0.33887226938665f, -0.32781137272105f,
		-0.31682789136456f, -0.30590985751916f,
		-0.29507167170646f, -0.28432141891085f,
		-0.27366340405625f, -0.26310532994603f,
		-0.25264803095722f, -0.24230168845974f,
		-0.23206908706791f, -0.22196526964149f,
		-0.21197358538056f, -0.20212501768103f,
		-0.19239667457267f, -0.18281725485142f,
		-0.17338081721706f, -0.16409588556669f,
		-0.15496070710605f, -0.1459766491187f,
		-0.13715517611934f, -0.12850028503878f,
		-0.120007798468f, -0.1116826931773f,
		-0.10353295311463f, -0.09555333528914f,
		-0.08775475365593f, -0.08013729344279f,
		-0.07269433008129f, -0.06544098531359f,
		-0.05837053268336f, -0.05148041767934f,
		-0.04478068215856f, -0.03827765720822f,
		-0.03195312745332f, -0.02582272888064f,
		-0.01988341292573f, -0.01412888273558f,
		-0.00857117491366f, -0.00320868968304f,
		0.00197656014503f, 0.00696368621617f,
		0.01176238327857f, 0.01637012582228f,
		0.02079970728622f, 0.02503075618909f,
		0.02908240060125f, 0.03295839306691f,
		0.03664181168133f, 0.04014582784127f,
		0.04347687821958f, 0.04663033051701f,
		0.04959786763445f, 0.05240938217366f,
		0.05504600343009f, 0.05751526919867f,
		0.0598166570809f, 0.06196027790387f,
		0.06394448059633f, 0.06576906686508f,
		0.06745250215166f, 0.06896640131951f,
		0.07035330735093f, 0.07158263647903f,
		0.07267746427299f, 0.0736406005762f,
		0.07446643947564f, 0.0751576255287f,
		0.07573057565061f, 0.07617483218536f,
		0.07650507183194f, 0.07672049241746f,
		0.07682300113923f, 0.07681739756964f,
		0.07670934904245f, 0.07649921704119f,
		0.07619924793396f, 0.07580083586584f,
		0.07531373362019f, 0.07474525581194f,
		0.07410036424342f, 0.07336202550803f,
		0.07256825833083f, 0.07170026731102f,
		0.07076287107266f, 0.06976302447127f,
		0.06870438283512f, 0.06760759851228f,
		0.06643675122104f, 0.0652247106438f,
		0.06397158980681f, 0.06268578081172f,
		0.06134551717207f, 0.05998374801761f,
		0.0585915683626f, 0.05716164501299f,
		0.05571736482138f, 0.05424527683589f,
		0.05276307465207f, 0.05125561555216f,
		0.04973857556014f, 0.04821657200672f,
		0.04668430272642f, 0.04514884056413f,
		0.04360975421304f, 0.04206490946367f,
		0.04053491705584f, 0.03900536794745f,
		0.03748128504252f, 0.03596975605542f,
		0.03446209487686f, 0.03297540810337f,
		0.03150176087389f, 0.03005026574279f,
		0.02860721736385f, 0.02718594296329f,
		0.02578758475467f, 0.02441609920285f,
		0.02306801692862f, 0.02174675502535f,
		0.02045317933555f, 0.01918724313698f,
		0.01794333813443f, 0.01673247129989f,
		0.01554055533423f, 0.01439046660792f,
		-0.01327182200351f, -0.01218499959508f,
		-0.01113155480321f, -0.01011502154986f,
		-0.00913253296085f, -0.00817982333726f,
		-0.00726158168517f, -0.00637922932685f,
		-0.00553372111088f, -0.004722259624f,
		-0.00394011240522f, -0.003193377839f,
		-0.00248267236449f, -0.00180394725893f,
		-0.00115681355227f, -0.00054642808664f,
		2.760451905E-005f, 0.0005832264248f,
		0.00109023290512f, 0.00157846825768f,
		0.0020274176185f, 0.00245085400321f,
		0.00284467578623f, 0.00320918858098f,
		0.00354012465507f, 0.00384564081246f,
		0.0041251642327f, 0.00438018617447f,
		0.00460395301471f, 0.004810946906f,
		0.00498396877629f, 0.00513822754514f,
		0.00527157587272f, 0.0053838975897f,
		0.0054753783077f, 0.005540436394f,
		0.0055917128663f, 0.00562661141932f,
		0.00563891995151f, 0.00564551969164f,
		0.00562206432097f, 0.00559380230045f,
		0.00554757145088f, 0.00548760401507f,
		0.00541967759307f, 0.00534716811982f,
		0.00524611661324f, 0.00514073539032f,
		0.00503930226013f, 0.00491376035745f,
		0.00479325608498f, 0.00466064606118f,
		0.00452098527825f, 0.00437307196781f,
		0.0042264269227f, 0.00408197531935f,
		0.00392074323703f, 0.00376039229104f,
		0.00360082681231f, 0.00344188741828f,
		0.00327396134847f, 0.00311254206525f,
		0.00294694477165f, 0.00278704643465f,
		0.00262017586902f, 0.00246256169126f,
		0.00230172547746f, 0.00214615835557f,
		0.00198411407369f, 0.00183482654224f,
		0.00168680832531f, 0.00154432198471f,
		0.00139024948272f, 0.00125778846475f,
		0.00112501551307f, 0.00098859883015f,
		0.00086084433262f, 0.00074580258865f,
		0.00062393761391f, 0.00051073884952f,
		0.0004026540216f, 0.00029495311041f,
		0.00020430170688f, 0.00010943831274f,
		1.349497418E-005f, -6.173344072E-005f,
		-0.00014463809349f, -0.0002098337344f,
		-0.00028969811748f, -0.00035011758756f,
		-0.00040951214522f, -0.00046063254803f,
		-0.00051455722108f, -0.00055645763906f,
		-0.0005946118933f, -0.00063415949025f,
		-0.00066504150893f, -0.00069179375372f,
		-0.00072153919876f, -0.00073193571525f,
		-0.00075300014201f, -0.00076307935757f,
		-0.0007757977331f, -0.00078014496257f,
		-0.000780366471f, -0.00077798694927f,
		-0.00078343322877f, -0.00077248485949f,
		-0.0007681371927f, -0.00074905980532f,
		-0.00074409418541f, -0.00072550431222f,
		-0.00071577364744f, -0.00069416146273f,
		-0.00067776907764f, -0.00065403333621f,
		-0.00063124935319f, -0.00061327473938f,
		-0.00058709304852f, -0.00056778025613f,
		-0.00054665656337f, -0.00052265642972f,
		-0.00050407143497f, -0.00048937912498f,
		-0.00048752279712f, -0.00049475180896f,
		-0.00056176925738f, -0.00055252865047f
	};
	int[] BIT_REVERSE_TABLE = {0, 16, 8, 24, 4, 20, 12, 28, 2, 18, 10, 26, 6, 22, 14,
		30, 1, 17, 9, 25, 5, 21, 13, 29, 3, 19, 11, 27, 7, 23, 15, 31};
	float[] DCT4_64_TABLE = {
		0.999924719333649f, 0.998118102550507f,
		0.993906974792480f, 0.987301409244537f,
		0.978317379951477f, 0.966976463794708f,
		0.953306019306183f, 0.937339007854462f,
		0.919113874435425f, 0.898674488067627f,
		0.876070082187653f, 0.851355195045471f,
		0.824589252471924f, 0.795836925506592f,
		0.765167236328125f, 0.732654273509979f,
		0.698376238346100f, 0.662415742874146f,
		0.624859452247620f, 0.585797846317291f,
		0.545324981212616f, 0.503538429737091f,
		0.460538715124130f, 0.416429549455643f,
		0.371317148208618f, 0.325310230255127f,
		0.278519600629807f, 0.231058135628700f,
		0.183039888739586f, 0.134580686688423f,
		0.085797272622585f, 0.036807164549828f,
		-1.012196302413940f, -1.059438824653626f,
		-1.104129195213318f, -1.146159529685974f,
		-1.185428738594055f, -1.221842169761658f,
		-1.255311965942383f, -1.285757660865784f,
		-1.313105940818787f, -1.337290763854981f,
		-1.358253836631775f, -1.375944852828980f,
		-1.390321016311646f, -1.401347875595093f,
		-1.408998727798462f, -1.413255214691162f,
		-1.414107084274292f, -1.411552190780640f,
		-1.405596733093262f, -1.396255016326904f,
		-1.383549690246582f, -1.367511272430420f,
		-1.348178386688232f, -1.325597524642944f,
		-1.299823284149170f, -1.270917654037476f,
		-1.238950133323669f, -1.203998088836670f,
		-1.166145324707031f, -1.125483393669128f,
		-1.082109928131104f, -1.036129593849182f,
		-0.987653195858002f, -0.936797380447388f,
		-0.883684754371643f, -0.828443288803101f,
		-0.771206021308899f, -0.712110757827759f,
		-0.651300072669983f, -0.588920354843140f,
		-0.525121808052063f, -0.460058242082596f,
		-0.393886327743530f, -0.326765477657318f,
		-0.258857429027557f, -0.190325915813446f,
		-0.121335685253143f, -0.052053272724152f,
		0.017354607582092f, 0.086720645427704f,
		0.155877828598022f, 0.224659323692322f,
		0.292899727821350f, 0.360434412956238f,
		0.427100926637650f, 0.492738455533981f,
		0.557188928127289f, 0.620297133922577f,
		0.681910991668701f, 0.741881847381592f,
		0.800065577030182f, 0.856321990489960f,
		0.910515367984772f, 0.962515234947205f,
		1.000000000000000f, 0.998795449733734f,
		0.995184719562531f, 0.989176511764526f,
		0.980785250663757f, 0.970031261444092f,
		0.956940352916718f, 0.941544055938721f,
		0.923879504203796f, 0.903989315032959f,
		0.881921231746674f, 0.857728600502014f,
		0.831469595432281f, 0.803207516670227f,
		0.773010432720184f, 0.740951120853424f,
		0.707106769084930f, 0.671558916568756f,
		0.634393274784088f, 0.595699310302734f,
		0.555570185184479f, 0.514102697372437f,
		0.471396654844284f, 0.427555114030838f,
		0.382683426141739f, 0.336889833211899f,
		0.290284633636475f, 0.242980122566223f,
		0.195090234279633f, 0.146730497479439f,
		0.098017133772373f, 0.049067649990320f,
		-1.000000000000000f, -1.047863125801086f,
		-1.093201875686646f, -1.135906934738159f,
		-1.175875544548035f, -1.213011503219605f,
		-1.247225046157837f, -1.278433918952942f,
		-1.306562900543213f, -1.331544399261475f,
		-1.353317975997925f, -1.371831417083740f,
		-1.387039899826050f, -1.398906826972961f,
		-1.407403707504273f, -1.412510156631470f,
		0f, -1.412510156631470f,
		-1.407403707504273f, -1.398906826972961f,
		-1.387039899826050f, -1.371831417083740f,
		-1.353317975997925f, -1.331544399261475f,
		-1.306562900543213f, -1.278433918952942f,
		-1.247225046157837f, -1.213011384010315f,
		-1.175875544548035f, -1.135907053947449f,
		-1.093201875686646f, -1.047863125801086f,
		-1.000000000000000f, -0.949727773666382f,
		-0.897167563438416f, -0.842446029186249f,
		-0.785694956779480f, -0.727051079273224f,
		-0.666655659675598f, -0.604654192924500f,
		-0.541196048259735f, -0.476434230804443f,
		-0.410524487495422f, -0.343625843524933f,
		-0.275899350643158f, -0.207508206367493f,
		-0.138617098331451f, -0.069392144680023f,
		0f, 0.069392263889313f,
		0.138617157936096f, 0.207508206367493f,
		0.275899469852448f, 0.343625962734222f,
		0.410524636507034f, 0.476434201002121f,
		0.541196107864380f, 0.604654192924500f,
		0.666655719280243f, 0.727051138877869f,
		0.785695075988770f, 0.842446029186249f,
		0.897167563438416f, 0.949727773666382f
	};
	float[][] FFT_TABLE = {
		{1.0f, 0.0f},
		{0.980785279337272f, -0.195090327375064f},
		{0.923879528329380f, -0.382683442461104f},
		{0.831469603195765f, -0.555570246648862f},
		{0.707106765732237f, -0.707106796640858f},
		{0.555570210304169f, -0.831469627480512f},
		{0.382683402077046f, -0.923879545057005f},
		{0.195090284503576f, -0.980785287864940f},
		{0.0f, -1.0f},
		{-0.195090370246552f, -0.980785270809601f},
		{-0.382683482845162f, -0.923879511601754f},
		{-0.555570282993553f, -0.831469578911016f},
		{-0.707106827549476f, -0.707106734823616f},
		{-0.831469651765257f, -0.555570173959476f},
		{-0.923879561784627f, -0.382683361692986f},
		{-0.980785296392607f, -0.195090241632088f}
	};
	float[] DST_TABLE = {0.7071067811865476f, 1.3065629648763766f, -0.9238795325112866f,
		-0.5411961001461967f, 0.7071067811865476f, 0.7071067811865476f,
		-0.7856949583871021f, 0.9807852804032304f, 1.1758756024193588f,
		-0.2758993792829430f, 0.8314696123025452f, 1.3870398453221475f,
		0.7071067811865476f, 1.3065629648763766f, -0.9238795325112866f,
		-0.5411961001461967f, 0.7071067811865476f, 1.3065629648763766f,
		-0.9238795325112866f, -0.5411961001461967f, -0.8971675863426361f,
		0.9951847266721968f, 1.0932018670017576f, -0.6666556584777466f,
		0.9569403357322089f, 1.2472250129866713f, -0.4105245275223571f,
		0.8819212643483549f, 1.3533180011743529f, -0.1386171691990915f,
		0.7730104533627370f, 1.4074037375263826f, 1.0478631305325901f,
		-0.9987954562051724f, -0.9497277818777548f, 1.2130114330978077f,
		-0.9700312531945440f, -0.7270510732912803f, 1.3315443865537255f,
		-0.9039892931234433f, -0.4764341996931612f, 1.3989068359730781f,
		-0.8032075314806453f, -0.2075082269882124f, 1.4125100802019777f,
		-0.6715589548470187f, 0.0693921705079402f, 1.3718313541934939f,
		-0.5141027441932219f, 0.3436258658070501f, 1.2784339185752409f,
		-0.3368898533922200f, 0.6046542117908008f, 1.1359069844201433f,
		-0.1467304744553624f, 0.8424460355094185f, 1.1758756024193588f,
		-0.9807852804032304f, -0.7856949583871021f, 1.3870398453221475f,
		-0.5555702330196022f, 0.2758993792829431f, 0.7856949583871022f,
		0.1950903220161283f, 1.1758756024193586f, -0.2758993792829430f,
		0.8314696123025452f, 1.3870398453221475f, 1.3065629648763766f,
		-0.9238795325112866f, -0.5411961001461967f, 0.5411961001461969f,
		0.3826834323650898f, 1.3065629648763766f, 1.3065629648763766f,
		-0.9238795325112866f, -0.5411961001461967f, 0.5411961001461969f,
		0.3826834323650898f, 1.3065629648763766f, 0.7071067811865474f,
		0.7071067811865474f, 0.7071067811865474f, 0.7071067811865474f,
		0.7071067811865474f, 0.7071067811865474f, 0.7071067811865474f,
		0.7071067811865474f, 0.5001506360206510f, 0.5013584524464084f,
		0.5037887256810443f, 0.5074711720725553f, 0.5124514794082247f,
		0.5187927131053328f, 0.5265773151542700f, 0.5359098169079920f,
		0.5469204379855088f, 0.5597698129470802f, 0.5746551840326600f,
		0.5918185358574165f, 0.6115573478825099f, 0.6342389366884031f,
		0.6603198078137061f, 0.6903721282002123f, 0.7251205223771985f,
		0.7654941649730891f, 0.8127020908144905f, 0.8683447152233481f,
		0.9345835970364075f, 1.0144082649970547f, 1.1120716205797176f,
		1.2338327379765710f, 1.3892939586328277f, 1.5939722833856311f,
		1.8746759800084078f, 2.2820500680051619f, 2.9246284281582162f,
		4.0846110781292477f, 6.7967507116736332f, 20.3738781672314530f};
	float[] DCT_TABLE = {0.7071067811865476f, 0.7071067811865476f, 0.7071067811865476f,
		0.7071067811865476f, 0.7071067811865476f, 0.7071067811865476f,
		0.7071067811865476f, 0.7071067811865476f, 0.7071067811865476f,
		0.7071067811865476f, 0.7071067811865476f, 0.7071067811865476f,
		0.7071067811865476f, 0.7071067811865476f, 0.7071067811865476f,
		0.7071067811865476f, -0.5411961001461969f, 0.9238795325112867f,
		1.3065629648763766f, 1.3065629648763770f, -0.3826834323650904f,
		0.5411961001461961f, -0.5411961001461969f, 0.9238795325112867f,
		1.3065629648763766f, 1.3065629648763770f, -0.3826834323650904f,
		0.5411961001461961f, -0.5411961001461969f, 0.9238795325112867f,
		1.3065629648763766f, 1.3065629648763770f, -0.3826834323650904f,
		0.5411961001461961f, -0.5411961001461969f, 0.9238795325112867f,
		1.3065629648763766f, 1.3065629648763770f, -0.3826834323650904f,
		0.5411961001461961f, -0.7856949583871021f, 0.9807852804032304f,
		1.1758756024193588f, 0.2758993792829431f, 0.5555702330196022f,
		1.3870398453221475f, 1.1758756024193591f, -0.1950903220161287f,
		0.7856949583871016f, 1.3870398453221473f, -0.8314696123025455f,
		-0.2758993792829436f, -0.7856949583871021f, 0.9807852804032304f,
		1.1758756024193588f, 0.2758993792829431f, 0.5555702330196022f,
		1.3870398453221475f, 1.1758756024193591f, -0.1950903220161287f,
		0.7856949583871016f, 1.3870398453221473f, -0.8314696123025455f,
		-0.2758993792829436f, -0.8971675863426361f, 0.9951847266721968f,
		1.0932018670017576f, -0.4105245275223571f, 0.8819212643483549f,
		1.3533180011743529f, 0.1386171691990915f, 0.6343932841636455f,
		1.4074037375263826f, 0.6666556584777466f, 0.2902846772544623f,
		1.2472250129866711f, 1.0932018670017574f, -0.0980171403295605f,
		0.8971675863426364f, 1.3533180011743529f, -0.4713967368259979f,
		0.4105245275223569f, 1.4074037375263826f, -0.7730104533627369f,
		-0.1386171691990913f, 1.2472250129866711f, -0.9569403357322089f,
		-0.6666556584777469f, -0.9751575901732920f, 0.9996988186962043f,
		1.0242400472191164f, -0.8700688593994936f, 0.9924795345987100f,
		1.1148902097979263f, -0.7566008898816587f, 0.9757021300385286f,
		1.1948033701953984f, -0.6358464401941451f, 0.9495281805930367f,
		1.2632099209919283f, -0.5089684416985408f, 0.9142097557035307f,
		1.3194510697085207f, -0.3771887988789273f, 0.8700869911087114f,
		1.3629851833384954f, -0.2417766217337384f, 0.8175848131515837f,
		1.3933930045694289f, -0.1040360035527077f, 0.7572088465064845f,
		1.4103816894602612f, 0.0347065382144002f, 0.6895405447370668f,
		1.4137876276885337f, 0.1731148370459795f, 0.6152315905806268f,
		1.4035780182072330f, 0.3098559453626100f, 0.5349976198870972f,
		1.3798511851368043f, 0.4436129715409088f, 0.4496113296546065f,
		1.3428356308501219f, 0.5730977622997509f, 0.3598950365349881f,
		1.2928878353697271f, 0.6970633083205415f, 0.2667127574748984f,
		1.2304888232703382f, 0.8143157536286401f, 0.1709618887603012f,
		1.1562395311492424f, 0.9237258930790228f, 0.0735645635996674f,
		1.0708550202783576f};
}
