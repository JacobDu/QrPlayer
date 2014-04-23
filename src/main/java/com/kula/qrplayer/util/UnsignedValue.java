package com.kula.qrplayer.util;

/**
 * The Class UnsignedValue.
 * 
 * @version 1.0
 */
public class UnsignedValue {

	public static final long toUint(final int uInt) {
		return uInt & 0x0FFFFFFFF;
	}

	/**
	 * To ushort.
	 * 
	 * @param ushort the ushort
	 * @return the int
	 */
	public static final int toUshort(final int ushort) {
		return ushort & 0x0FFFF;
	}

	/**
	 * To ubyte.
	 * 
	 * @param ubyte the ubyte
	 * @return the int
	 */
	public static final int toUbyte(final byte ubyte) {
		return ubyte & 0x0FF;
	}
}
