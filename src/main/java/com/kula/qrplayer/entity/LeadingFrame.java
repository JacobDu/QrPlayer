package com.kula.qrplayer.entity;

/**
 * The Class LeadingFrame.
 * 
 * @version 1.0
 */
public class LeadingFrame {

    /**
     * Gets the version.
     * 
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version the new version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Gets the compr type.
     * 
     * @return the compr type
     */
    public CompressType getComprType() {
        return comprType;
    }

    /**
     * Sets the compr type.
     * 
     * @param comprType the new compr type
     */
    public void setComprType(CompressType comprType) {
        this.comprType = comprType;
    }

    /**
     * Gets the slice count.
     * 
     * @return the slice count
     */
    public int getSliceCount() {
        return sliceCount;
    }

    /**
     * Sets the slice count.
     * 
     * @param sliceCount the new slice count
     */
    public void setSliceCount(int sliceCount) {
        this.sliceCount = sliceCount;
    }

    /**
     * Gets the orig file name.
     * 
     * @return the orig file name
     */
    public String getOrigFileName() {
        return origFileName;
    }

    /**
     * Sets the orig file name.
     * 
     * @param origFileName the new orig file name
     */
    public void setOrigFileName(String origFileName) {
        this.origFileName = origFileName;
    }

    /**
     * Gets the slice interval.
     * 
     * @return the slice interval
     */
    public long getSliceInterval() {
        return sliceInterval;
    }

    /**
     * Sets the slice interval.
     * 
     * @param sliceInterval the new slice interval
     */
    public void setSliceInterval(long sliceInterval) {
        this.sliceInterval = sliceInterval;
    }

    /** The version. */
    private int version;

    /** The compress type. */
    private CompressType comprType;

    /** The slice count. */
    private int sliceCount;

    /** The original file name. */
    private String origFileName;

    /** The slice interval. unit is ms */
    private long sliceInterval;
}
