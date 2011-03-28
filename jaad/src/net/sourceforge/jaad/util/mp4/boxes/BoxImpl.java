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
package net.sourceforge.jaad.util.mp4.boxes;

import java.io.IOException;
import net.sourceforge.jaad.util.mp4.MP4InputStream;

public abstract class BoxImpl implements Box {

	protected static final int MASK8 = 0xFF;
	protected static final int MASK16 = 0xFFFF;
	private String name, shortName;
	protected long size, type, left;
	protected Box parent;

	protected BoxImpl(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	void setParams(long size, long type, Box parent, long left) {
		this.size = size;
		this.type = type;
		this.parent = parent;
		this.left = left;
	}

	long getLeft() {
		return left;
	}

	/**
	 * Decodes the specified input stream by reading this box and all of its
	 * children (if any) and returns the number of bytes left in the box (which
	 * should be normally 0).
	 * @param in an input stream
	 * @throws IOException if an reading error occurs
	 */
	public abstract void decode(MP4InputStream in) throws IOException;

	public long getType() {
		return type;
	}

	public long getSize() {
		return size;
	}

	public Box getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	protected double getFloatingPoint(long l, int mask) {
		long mantissa = (l&mask)<<52;
		long exponent = l&mask;
		return Double.longBitsToDouble(mantissa|exponent);
	}
}
