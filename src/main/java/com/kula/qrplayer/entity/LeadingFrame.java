package com.kula.qrplayer.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The Class LeadingFrame.
 * 
 * @version 1.0
 */
public class LeadingFrame {

    /** The Constant MAGIC_LEN. */
    public static final int MAGIC_LEN = 4;

    /** The Constant MAGIC. */
    public static final byte[] MAGIC =
            new byte[] { (byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF };

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
    public void setOrigFileName(final String origFileName) {
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

    /**
     * To bytes.
     * 
     * @return the byte[]
     */
    public byte[] toBytes() {
        // magic 4 bytes 0xdeadbeef
        // version 4 bytes int
        // comprType 1 byte
        // sliceCount 4 bytes int
        // sliceDuration 8 bytes long
        // origFileSize 4 bytes int
        // compressedSize 4 bytes int
        // fileNameLen 4 bytes int
        // fileName
        try (final ByteArrayOutputStream result = new ByteArrayOutputStream();) {
            result.write(MAGIC);
            result.write(ByteBuffer.allocate(4).putInt(version).array());
            result.write(comprType.getId());
            result.write(ByteBuffer.allocate(4).putInt(sliceCount).array());
            result.write(ByteBuffer.allocate(8).putLong(sliceDuration).array());
            result.write(ByteBuffer.allocate(4).putInt(origFileSize).array());
            result.write(ByteBuffer.allocate(4).putInt(compressedSize).array());
            final byte[] fileName = origFileName.getBytes();
            result.write(ByteBuffer.allocate(4).putInt(fileName.length).array());
            result.write(fileName);
            return result.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Can not get bytes from leading frame.");
        }
    }

    /**
     * Creates the leading frame.
     * 
     * @param buffer the buffer
     * @return the leading frame
     */
    public static LeadingFrame createLeadingFrame(final ByteBuffer buffer) {
        final LeadingFrame result = new LeadingFrame();
        // magic 4 bytes 0xdeadbeef
        // version 4 bytes int
        // comprType 1 byte
        // sliceCount 4 bytes int
        // sliceDuration 8 bytes long
        // origFileSize 4 bytes int
        // compressedSize 4 bytes int
        // fileNameLen 4 bytes int
        // fileName
        final byte[] magic = new byte[MAGIC_LEN];
        buffer.get(magic);
        if (!Arrays.equals(magic, MAGIC)) {
            throw new IllegalArgumentException("The buffer is not a legal leading frame.");
        }
        result.setVersion(buffer.getInt());
        result.setComprType(CompressType.getById(buffer.get()));
        result.setSliceCount(buffer.getInt());
        result.setSliceDuration(buffer.getLong());
        result.setOrigFileSize(buffer.getInt());
        result.setCompressedSize(buffer.getInt());
        final int fileNameLen = buffer.getInt();
        final byte[] fileNameArray = new byte[fileNameLen];
        buffer.get(fileNameArray);
        result.setOrigFileName(new String(fileNameArray));
        return result;
    }

    /** The version. */
    private int version;

    /** The compress type. */
    private CompressType comprType;

    /** The slice count. */
    private int sliceCount;

    /** The slice duration. unit is ms */
    private long sliceDuration;

    /** The orig file size. */
    private int origFileSize;

    /** The compressed size. */
    private int compressedSize;

    /** The original file name. */
    private String origFileName;
}
