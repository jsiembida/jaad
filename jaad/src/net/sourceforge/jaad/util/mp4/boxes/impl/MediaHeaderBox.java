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
package net.sourceforge.jaad.util.mp4.boxes.impl;

import net.sourceforge.jaad.util.mp4.boxes.FullBox;
import net.sourceforge.jaad.util.mp4.MP4InputStream;
import java.io.IOException;

public class MediaHeaderBox extends FullBox {

	private long timeScale;

	public MediaHeaderBox() {
		super("Media Header Box", "mdhd");
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
		super.decode(in);
		final int len = (version==1) ? 8 : 4;
		in.skipBytes(2*len); //creationTime, modificationTime

		timeScale = in.readBytes(4);
		in.skipBytes(len); //duration

		in.skipBytes(4); //language
		left -= 8+(3*len);
	}

	public long getTimeScale() {
		return timeScale;
	}
}