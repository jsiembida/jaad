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
package net.sourceforge.jaad.mp4.api;

import java.util.Date;

class Utils {

	private static final long DATE_OFFSET = 2082850791998l;

	static Date getDate(long time) {
		return new Date(time*1000-DATE_OFFSET);
	}

	static long bytesToLong(byte[] b, int off, int len) {
		long l = 0;
		for(int i = 0; i<len; i++) {
			l <<= 8;
			l |= b[off+i];
		}
		return l;
	}
}
