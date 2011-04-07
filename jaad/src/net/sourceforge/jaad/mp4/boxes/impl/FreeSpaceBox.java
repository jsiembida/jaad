package net.sourceforge.jaad.mp4.boxes.impl;

import java.io.IOException;
import net.sourceforge.jaad.mp4.MP4InputStream;
import net.sourceforge.jaad.mp4.boxes.BoxImpl;

public class FreeSpaceBox extends BoxImpl {

	public FreeSpaceBox() {
		super("Free Space Box");
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
		//no need to read, box will be skipped
	}
}
