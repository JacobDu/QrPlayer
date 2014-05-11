package com.kula.qrplayer.entity;

import java.nio.ByteBuffer;

import org.apache.commons.lang3.ArrayUtils;

/**
 * The Class SliceFrame.
 * 
 * @version 1.0
 */
public class SliceFrame {

    /**
     * Gets the index.
     * 
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index.
     * 
     * @param index the new index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets the slice.
     * 
     * @return the slice
     */
    public byte[] getSlice() {
        return slice;
    }

    /**
     * Sets the slice.
     * 
     * @param slice the new slice
     */
    public void setSlice(byte[] slice) {
        this.slice = slice;
    }

    /**
     * Instantiates a new slice frame.
     * 
     * @param index the index
     * @param slice the slice
     */
    public SliceFrame(final int index, final byte[] slice) {
        super();
        this.index = index;
        this.slice = slice;
    }

    /**
     * To bytes.
     * 
     * @return the byte[]
     */
    public byte[] toBytes() {
        return ArrayUtils.addAll(ByteBuffer.allocate(4).putInt(index).array(), slice);
    }

    /** The index. base on ZREO */
    private int index;

    /** The matrix. */
    private byte[] slice;
}
