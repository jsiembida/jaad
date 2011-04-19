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

import java.io.IOException;
import net.sourceforge.jaad.mp4.MP4InputStream;
import net.sourceforge.jaad.mp4.boxes.FullBox;

public class DataEntryUrnBox extends FullBox {

	private boolean inFile;
	private String referenceName, location;

	public DataEntryUrnBox() {
		super("Data Entry Urn Box");
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
		super.decode(in);

		inFile = (flags&1)==1;
		if(!inFile) {
			referenceName = in.readUTFString((int) left, MP4InputStream.UTF8);
			left -= referenceName.length()+1;
			if(left>0) {
				location = in.readUTFString((int) left, MP4InputStream.UTF8);
				left -= location.length()+1;
			}
		}
	}

	public boolean isInFile() {
		return inFile;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public String getLocation() {
		return location;
	}
}
