package com.kula.qrplayer.entity;

/**
 * The Class SliceFrame.
 * 
 * @version 1.0
 */
public class SliceFrame {

    /**
     * Gets the timestamp.
     * 
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     * 
     * @param timestamp the new timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

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

    /** The timestamp. unit is ms */
    private long timestamp;

    /** The index. base on ZREO */
    private int index;

    /** The matrix. */
    private byte[] slice;
}
