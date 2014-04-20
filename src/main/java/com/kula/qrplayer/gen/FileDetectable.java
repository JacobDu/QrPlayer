package com.kula.qrplayer.gen;

import java.io.File;

import com.kula.qrplayer.entity.CompressType;
import com.kula.qrplayer.entity.SliceFrame;

/**
 * The Interface FileDetectable.
 * 
 * @version 1.0
 */
public interface FileDetectable extends Iterable<SliceFrame> {

    /**
     * Detect.
     * 
     * @param input the input
     */
    void detect(File input);

    /**
     * Gets the file size.
     * 
     * @return the file size
     */
    int getFileSize();

    /**
     * Gets the slice count.
     * 
     * @return the slice count
     */
    int getSliceCount();

    /**
     * Gets the file name.
     * 
     * @return the file name
     */
    String getFileName();

    /**
     * Gets the compress type.
     * 
     * @return the compress type
     */
    CompressType getCompressType();

    /**
     * Gets the compressed size.
     * 
     * @return the compressed size
     */
    int getCompressedSize();

    /**
     * Cleanup.
     */
    void cleanup();

}
