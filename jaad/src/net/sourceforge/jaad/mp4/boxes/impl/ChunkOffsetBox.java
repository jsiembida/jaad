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
package net.sourceforge.jaad.mp4.boxes.impl;

import net.sourceforge.jaad.mp4.boxes.BoxTypes;
import net.sourceforge.jaad.mp4.boxes.FullBox;
import net.sourceforge.jaad.mp4.MP4InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChunkOffsetBox extends FullBox {

	private final List<Long> chunks;

	public ChunkOffsetBox() {
		super("Chunk Offset Box", "stco");
		chunks = new ArrayList<Long>();
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
		super.decode(in);
		final int entryCount = (int) in.readBytes(4);
		left -= 4;
		final int len = (type==BoxTypes.CHUNK_LARGE_OFFSET_BOX) ? 8 : 4;
		
		long x;
		for(int i = 0; i<entryCount; i++) {
			x = in.readBytes(len);
			chunks.add(Long.valueOf(x));
			left -= len;
		}
	}

	public List<Long> getChunks() {
		return chunks;
	}
}
