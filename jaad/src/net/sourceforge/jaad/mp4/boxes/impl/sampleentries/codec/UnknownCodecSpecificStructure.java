package net.sourceforge.jaad.mp4.boxes.impl.sampleentries.codec;

import java.io.IOException;
import net.sourceforge.jaad.mp4.MP4InputStream;

public class UnknownCodecSpecificStructure extends CodecSpecificStructure {

	UnknownCodecSpecificStructure() {
		super(0);
	}

	void decode(MP4InputStream in) throws IOException {
	}
}
