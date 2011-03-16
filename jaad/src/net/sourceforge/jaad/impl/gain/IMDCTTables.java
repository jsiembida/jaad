package jaad.impl.gain;

interface IMDCTTables {

	//pre-twiddling tables
	float[][] IMDCT_TABLE_256 = {
		{1.0f, -0.0f},
		{0.9996988f, -0.024541229f},
		{0.99879545f, -0.049067676f},
		{0.99729043f, -0.07356457f},
		{0.9951847f, -0.09801714f},
		{0.99247956f, -0.12241068f},
		{0.9891765f, -0.14673047f},
		{0.98527765f, -0.1709619f},
		{0.98078525f, -0.19509032f},
		{0.9757021f, -0.21910124f},
		{0.97003126f, -0.2429802f},
		{0.96377605f, -0.26671278f},
		{0.95694035f, -0.29028466f},
		{0.94952816f, -0.31368175f},
		{0.94154406f, -0.33688986f},
		{0.9329928f, -0.35989505f},
		{0.9238795f, -0.38268346f},
		{0.9142097f, -0.40524134f},
		{0.9039893f, -0.42755508f},
		{0.8932243f, -0.44961134f},
		{0.88192123f, -0.47139674f},
		{0.87008697f, -0.49289823f},
		{0.8577286f, -0.51410276f},
		{0.8448536f, -0.53499764f},
		{0.8314696f, -0.55557024f},
		{0.8175848f, -0.5758082f},
		{0.8032075f, -0.5956993f},
		{0.7883464f, -0.61523163f},
		{0.77301043f, -0.63439333f},
		{0.7572088f, -0.65317285f},
		{0.7409511f, -0.671559f},
		{0.7242471f, -0.68954057f},
		{0.70710677f, -0.70710677f},
		{0.6895405f, -0.7242471f},
		{0.6715589f, -0.7409512f},
		{0.6531728f, -0.7572089f},
		{0.6343933f, -0.77301043f},
		{0.6152316f, -0.7883464f},
		{0.5956993f, -0.8032075f},
		{0.57580817f, -0.8175848f},
		{0.5555702f, -0.83146966f},
		{0.53499764f, -0.8448536f},
		{0.5141027f, -0.85772866f},
		{0.4928982f, -0.87008697f},
		{0.47139665f, -0.8819213f},
		{0.4496113f, -0.8932243f},
		{0.4275551f, -0.9039893f},
		{0.40524128f, -0.9142098f},
		{0.38268343f, -0.9238795f},
		{0.35989496f, -0.9329928f},
		{0.33688983f, -0.94154406f},
		{0.31368166f, -0.9495282f},
		{0.29028463f, -0.95694035f},
		{0.26671275f, -0.96377605f},
		{0.24298012f, -0.97003126f},
		{0.21910122f, -0.9757021f},
		{0.19509023f, -0.9807853f},
		{0.17096186f, -0.98527765f},
		{0.1467305f, -0.9891765f},
		{0.122410625f, -0.99247956f},
		{0.098017134f, -0.9951847f},
		{0.07356449f, -0.99729043f},
		{0.04906765f, -0.99879545f},
		{0.024541136f, -0.9996988f},
		{-4.371139E-8f, -1.0f},
		{-0.024541223f, -0.9996988f},
		{-0.04906774f, -0.99879545f},
		{-0.073564574f, -0.99729043f},
		{-0.09801722f, -0.9951847f},
		{-0.12241071f, -0.9924795f},
		{-0.14673057f, -0.9891765f},
		{-0.17096195f, -0.98527765f},
		{-0.19509032f, -0.98078525f},
		{-0.21910131f, -0.9757021f},
		{-0.2429802f, -0.97003126f},
		{-0.26671284f, -0.96377605f},
		{-0.29028472f, -0.9569403f},
		{-0.31368172f, -0.94952816f},
		{-0.33688992f, -0.94154406f},
		{-0.35989505f, -0.9329928f},
		{-0.38268352f, -0.9238795f},
		{-0.40524134f, -0.9142097f},
		{-0.42755508f, -0.9039893f},
		{-0.44961137f, -0.8932243f},
		{-0.47139683f, -0.88192123f},
		{-0.49289817f, -0.870087f},
		{-0.51410276f, -0.8577286f},
		{-0.5349977f, -0.8448535f},
		{-0.55557036f, -0.83146954f},
		{-0.57580817f, -0.8175848f},
		{-0.59569937f, -0.8032075f},
		{-0.6152317f, -0.78834635f},
		{-0.6343933f, -0.7730105f},
		{-0.65317285f, -0.7572088f},
		{-0.67155904f, -0.74095106f},
		{-0.6895407f, -0.724247f},
		{-0.70710677f, -0.70710677f},
		{-0.72424716f, -0.6895405f},
		{-0.74095124f, -0.67155886f},
		{-0.7572088f, -0.65317285f},
		{-0.7730105f, -0.6343933f},
		{-0.78834647f, -0.6152315f},
		{-0.80320764f, -0.59569913f},
		{-0.8175848f, -0.57580817f},
		{-0.83146966f, -0.5555702f},
		{-0.84485364f, -0.53499746f},
		{-0.8577286f, -0.51410276f},
		{-0.870087f, -0.49289814f},
		{-0.88192135f, -0.47139663f},
		{-0.8932243f, -0.44961137f},
		{-0.9039893f, -0.42755505f},
		{-0.9142098f, -0.40524122f},
		{-0.9238796f, -0.38268328f},
		{-0.9329928f, -0.35989505f},
		{-0.9415441f, -0.3368898f},
		{-0.9495282f, -0.3136816f},
		{-0.95694035f, -0.29028472f},
		{-0.96377605f, -0.26671273f},
		{-0.97003126f, -0.24298008f},
		{-0.97570217f, -0.21910107f},
		{-0.9807853f, -0.19509031f},
		{-0.98527765f, -0.17096181f},
		{-0.9891765f, -0.14673033f},
		{-0.9924795f, -0.1224107f},
		{-0.9951847f, -0.0980171f},
		{-0.9972905f, -0.07356445f},
		{-0.99879545f, -0.049067486f},
		{-0.9996988f, -0.02454121f}
	};
	float[][] IMDCT_TABLE_32 = {
		{1.0f, -0.0f},
		{0.98078525f, -0.19509032f},
		{0.9238795f, -0.38268346f},
		{0.8314696f, -0.55557024f},
		{0.70710677f, -0.70710677f},
		{0.5555702f, -0.83146966f},
		{0.38268343f, -0.9238795f},
		{0.19509023f, -0.9807853f},
		{-4.371139E-8f, -1.0f},
		{-0.19509032f, -0.98078525f},
		{-0.38268352f, -0.9238795f},
		{-0.55557036f, -0.83146954f},
		{-0.70710677f, -0.70710677f},
		{-0.83146966f, -0.5555702f},
		{-0.9238796f, -0.38268328f},
		{-0.9807853f, -0.19509031f}
	};
	//post-twiddling tables
	float[][] IMDCT_POST_TABLE_256 = {
		{0.49232805f, 0.50766724f, 0.50147516f, 0.49840719f},
		{0.47697723f, 0.5229804f, 0.50407255f, 0.4948688f},
		{0.46162924f, 0.5382531f, 0.50619966f, 0.49086043f},
		{0.44629848f, 0.5534709f, 0.50785726f, 0.4863832f},
		{0.43099934f, 0.5686195f, 0.5090466f, 0.48143846f},
		{0.41574615f, 0.58368444f, 0.5097693f, 0.47602817f},
		{0.40055317f, 0.5986516f, 0.5100275f, 0.47015458f},
		{0.38543463f, 0.6135067f, 0.50982374f, 0.46382055f},
		{0.37040454f, 0.6282357f, 0.5091608f, 0.45702913f},
		{0.35547704f, 0.64282453f, 0.50804234f, 0.4497841f},
		{0.34066594f, 0.65725935f, 0.506472f, 0.44208938f},
		{0.32598507f, 0.6715264f, 0.5044541f, 0.43394947f},
		{0.31144798f, 0.6856121f, 0.5019932f, 0.42536932f},
		{0.29706824f, 0.6995029f, 0.4990945f, 0.41635424f},
		{0.2828591f, 0.7131856f, 0.49576342f, 0.40690988f},
		{0.26883373f, 0.726647f, 0.4920059f, 0.39704242f},
		{0.25500503f, 0.73987424f, 0.48782825f, 0.3867584f},
		{0.24138579f, 0.7528547f, 0.48323712f, 0.3760647f},
		{0.22798851f, 0.76557565f, 0.4782396f, 0.36496866f},
		{0.21482554f, 0.7780249f, 0.47284314f, 0.35347793f},
		{0.20190886f, 0.79019046f, 0.46705556f, 0.3416006f},
		{0.18925038f, 0.8020605f, 0.4608851f, 0.3293451f},
		{0.17686158f, 0.8136235f, 0.45434028f, 0.3167202f},
		{0.16475382f, 0.8248682f, 0.44743007f, 0.30373502f},
		{0.15293804f, 0.8357836f, 0.44016364f, 0.2903991f},
		{0.14142501f, 0.84635913f, 0.4325506f, 0.2767222f},
		{0.13022512f, 0.85658425f, 0.42460087f, 0.26271448f},
		{0.11934847f, 0.86644906f, 0.41632465f, 0.24838635f},
		{0.10880476f, 0.8759437f, 0.40773243f, 0.23374856f},
		{0.09860358f, 0.8850589f, 0.39883506f, 0.21881217f},
		{0.08875397f, 0.89378536f, 0.38964373f, 0.20358856f},
		{0.0792647f, 0.9021145f, 0.38016963f, 0.18808924f},
		{0.07014418f, 0.91003793f, 0.37042463f, 0.1723262f},
		{0.061400414f, 0.91754776f, 0.36042035f, 0.1563114f},
		{0.05304113f, 0.92463624f, 0.35016915f, 0.14005733f},
		{0.0450736f, 0.9312961f, 0.3396833f, 0.12357649f},
		{0.037504703f, 0.9375206f, 0.32897532f, 0.106881686f},
		{0.03034103f, 0.9433032f, 0.3180581f, 0.08998602f},
		{0.023588628f, 0.94863784f, 0.30694458f, 0.07290262f},
		{0.01725325f, 0.95351887f, 0.29564792f, 0.055644885f},
		{0.011340171f, 0.95794106f, 0.28418133f, 0.038226277f},
		{0.005854279f, 0.9618995f, 0.27255845f, 0.020660654f},
		{8.0010295E-4f, 0.96538985f, 0.26079288f, 0.0029617846f},
		{-0.003818363f, 0.9684081f, 0.24889828f, -0.014856413f},
		{-0.007997453f, 0.9709507f, 0.23688862f, -0.0327797f},
		{-0.011734039f, 0.9730145f, 0.22477779f, -0.050794043f},
		{-0.015025228f, 0.97459674f, 0.21258f, -0.06888495f},
		{-0.017868847f, 0.97569525f, 0.20030917f, -0.08703829f},
		{-0.020262927f, 0.9763082f, 0.1879797f, -0.10523948f},
		{-0.022206068f, 0.9764342f, 0.17560576f, -0.12347408f},
		{-0.023697197f, 0.9760722f, 0.16320162f, -0.14172764f},
		{-0.024735779f, 0.9752219f, 0.15078172f, -0.15998542f},
		{-0.025321692f, 0.97388303f, 0.13836022f, -0.17823319f},
		{-0.025455266f, 0.97205615f, 0.12595156f, -0.19645613f},
		{-0.025137246f, 0.96974206f, 0.113570005f, -0.21463984f},
		{-0.024368823f, 0.966942f, 0.10122979f, -0.23276988f},
		{-0.023151666f, 0.96365774f, 0.088945225f, -0.25083166f},
		{-0.021487802f, 0.9598913f, 0.07673041f, -0.26881093f},
		{-0.019379854f, 0.9556455f, 0.06459953f, -0.28669322f},
		{-0.016830623f, 0.95092314f, 0.0525665f, -0.3044645f},
		{-0.013843596f, 0.9457279f, 0.04064539f, -0.32211035f},
		{-0.010422587f, 0.9400635f, 0.02884984f, -0.33961698f},
		{-0.0065717697f, 0.9339343f, 0.017193556f, -0.35697052f},
		{-0.002295822f, 0.92734504f, 0.0056901723f, -0.374157f},
		{0.0024001896f, 0.92030096f, -0.0056470186f, -0.3911631f},
		{0.0075107515f, 0.91280746f, -0.016804636f, -0.40797502f},
		{0.013030022f, 0.90487075f, -0.027769819f, -0.4245798f},
		{0.018951744f, 0.896497f, -0.038529605f, -0.44096428f},
		{0.025269121f, 0.88769305f, -0.049071252f, -0.4571154f},
		{0.03197518f, 0.8784661f, -0.059382424f, -0.47302073f},
		{0.03906241f, 0.86882365f, -0.06945078f, -0.48866767f},
		{0.046523094f, 0.85877365f, -0.07926449f, -0.5040442f},
		{0.054348946f, 0.84832436f, -0.08881168f, -0.51913816f},
		{0.06253144f, 0.8374845f, -0.09808087f, -0.533938f},
		{0.07106161f, 0.82626295f, -0.107060805f, -0.5484321f},
		{0.079930276f, 0.81466925f, -0.11574057f, -0.56260943f},
		{0.0891279f, 0.8027128f, -0.124109596f, -0.57645917f},
		{0.098644555f, 0.7904038f, -0.13215744f, -0.58997077f},
		{0.10847002f, 0.7777525f, -0.13987413f, -0.6031339f},
		{0.11859363f, 0.7647697f, -0.14724979f, -0.61593866f},
		{0.12900484f, 0.75146604f, -0.15427522f, -0.6283754f},
		{0.13969228f, 0.73785305f, -0.16094121f, -0.640435f},
		{0.15064475f, 0.7239419f, -0.16723916f, -0.65210843f},
		{0.16185057f, 0.7097445f, -0.17316064f, -0.6633872f},
		{0.1732978f, 0.6952729f, -0.1786977f, -0.674263f},
		{0.18497421f, 0.6805394f, -0.18384269f, -0.684728f},
		{0.19686763f, 0.6655563f, -0.18858838f, -0.69477504f},
		{0.20896536f, 0.65033644f, -0.1929279f, -0.7043968f},
		{0.22125456f, 0.6348928f, -0.19685477f, -0.71358657f},
		{0.23372234f, 0.61923826f, -0.20036295f, -0.7223382f},
		{0.24635552f, 0.6033862f, -0.20344675f, -0.7306459f},
		{0.2591407f, 0.58735025f, -0.20610088f, -0.73850405f},
		{0.27206418f, 0.5711441f, -0.2083205f, -0.7459076f},
		{0.28511274f, 0.554781f, -0.21010125f, -0.752852f},
		{0.2982724f, 0.5382753f, -0.21143904f, -0.75933313f},
		{0.31152916f, 0.521641f, -0.21233031f, -0.765347f},
		{0.3248692f, 0.50489205f, -0.21277195f, -0.7708905f},
		{0.33827832f, 0.48804274f, -0.2127612f, -0.77596056f},
		{0.3517424f, 0.47110736f, -0.21229571f, -0.7805547f},
		{0.365247f, 0.4541005f, -0.21137378f, -0.78467095f},
		{0.37877813f, 0.43703625f, -0.20999387f, -0.78830767f},
		{0.39232132f, 0.41992924f, -0.20815507f, -0.79146373f},
		{0.40586197f, 0.40279418f, -0.20585686f, -0.79413843f},
		{0.4193862f, 0.3856451f, -0.20309913f, -0.79633147f},
		{0.43287942f, 0.36849675f, -0.19988227f, -0.798043f},
		{0.4463272f, 0.3513636f, -0.19620705f, -0.79927367f},
		{0.45971522f, 0.33426026f, -0.19207478f, -0.80002457f},
		{0.47302932f, 0.3172009f, -0.18748704f, -0.80029714f},
		{0.48625526f, 0.30019996f, -0.18244597f, -0.8000933f},
		{0.49937868f, 0.2832719f, -0.17695424f, -0.79941547f},
		{0.51238585f, 0.2664307f, -0.1710147f, -0.79826653f},
		{0.52526253f, 0.24969055f, -0.16463086f, -0.7966496f},
		{0.53799486f, 0.23306563f, -0.15780658f, -0.7945684f},
		{0.5505693f, 0.21656957f, -0.15054607f, -0.7920271f},
		{0.5629722f, 0.20021626f, -0.14285406f, -0.7890301f},
		{0.5751899f, 0.18401925f, -0.13473573f, -0.7855824f},
		{0.5872092f, 0.1679922f, -0.12619662f, -0.78168947f},
		{0.599017f, 0.15214804f, -0.117242515f, -0.77735686f},
		{0.61060053f, 0.13650006f, -0.10787988f, -0.7725909f},
		{0.62194663f, 0.121061325f, -0.09811556f, -0.7673981f},
		{0.6330432f, 0.10584408f, -0.08795637f, -0.7617854f},
		{0.64387786f, 0.09086105f, -0.07741001f, -0.7557601f},
		{0.6544384f, 0.0761244f, -0.06648436f, -0.7493299f},
		{0.6647129f, 0.061646253f, -0.055187732f, -0.74250305f},
		{0.67469f, 0.047438115f, -0.043528587f, -0.7352879f},
		{0.6843584f, 0.03351158f, -0.031515926f, -0.7276931f},
		{0.693707f, 0.01987794f, -0.019159257f, -0.71972805f},
		{0.70272505f, 0.006547779f, -0.0064679384f, -0.711402f}
	};
	float[][] IMDCT_POST_TABLE_32 = {
		{0.43864408f, 0.56105477f, 0.5085104f, 0.48396915f},
		{0.3186977f, 0.67859274f, 0.5032787f, 0.4297141f},
		{0.20833567f, 0.7841439f, 0.46999773f, 0.34758708f},
		{0.114034384f, 0.87124324f, 0.41206735f, 0.24110544f},
		{0.041238904f, 0.9344632f, 0.33435628f, 0.115255035f},
		{-0.0059630573f, 0.9697391f, 0.24290696f, -0.023805834f},
		{-0.02508533f, 0.9746135f, 0.14457026f, -0.16911149f},
		{-0.015391618f, 0.9483844f, 0.046591163f, -0.3133039f},
		{0.022061408f, 0.8921483f, -0.043828517f, -0.44906986f},
		{0.0844886f, 0.8087357f, -0.119964585f, -0.5695759f},
		{0.16754475f, 0.7025422f, -0.1759777f, -0.66887593f},
		{0.26558587f, 0.57926774f, -0.20726526f, -0.7422629f},
		{0.37201017f, 0.44557464f, -0.21074113f, -0.7865493f},
		{0.4796542f, 0.30869222f, -0.18502301f, -0.80025464f},
		{0.5812251f, 0.17598371f, -0.13051844f, -0.7836913f},
		{0.66973937f, 0.054507732f, -0.049402922f, -0.73894346f}
	};
}
