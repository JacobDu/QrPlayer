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
     * Gets the orig file size.
     * 
     * @return the orig file size
     */
    public int getOrigFileSize() {
        return origFileSize;
    }

    /**
     * Sets the orig file size.
     * 
     * @param origFileSize the new orig file size
     */
    public void setOrigFileSize(int origFileSize) {
        this.origFileSize = origFileSize;
    }

    /**
     * Gets the compressed size.
     * 
     * @return the compressed size
     */
    public int getCompressedSize() {
        return compressedSize;
    }

    /**
     * Sets the compressed size.
     * 
     * @param compressedSize the new compressed size
     */
    public void setCompressedSize(int compressedSize) {
        this.compressedSize = compressedSize;
    }

    /**
     * Gets the slice duration.
     * 
     * @return the slice duration
     */
    public long getSliceDuration() {
        return sliceDuration;
    }

    /**
     * Sets the slice duration.
     * 
     * @param sliceDuration the new slice duration
     */
    public void setSliceDuration(long sliceDuration) {
        this.sliceDuration = sliceDuration;
    }

    /** The version. */
    private int version;

    /** The compress type. */
    private CompressType comprType;

    /** The slice count. */
    private int sliceCount;

    /** The original file name. */
    private String origFileName;

    /** The slice duration. unit is ms */
    private long sliceDuration;

    /** The orig file size. */
    private int origFileSize;

    /** The compressed size. */
    private int compressedSize;
}
