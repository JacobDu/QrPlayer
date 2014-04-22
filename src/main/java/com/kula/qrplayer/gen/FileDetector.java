package com.kula.qrplayer.gen;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.xerial.snappy.Snappy;

import com.kula.qrplayer.entity.CompressType;
import com.kula.qrplayer.entity.SliceFrame;
import com.kula.qrplayer.util.Configuration;

/**
 * The Class FileDetector.
 * Detect file meta info and split file to slice.
 * 
 * @version 1.0
 */
public class FileDetector implements FileDetectable {

    /** The Constant MAX_FILE_SIZE. */
    public static final int MAX_FILE_SIZE = Configuration.getINSTANCE().getMaxFileSize();

    /** The Constant SLICE_SIZE. */
    public static final int SLICE_SIZE = Configuration.getINSTANCE().getSliceSize();

    /** The Constant COMPRESS_TYPE. */
    public static final CompressType COMPRESS_TYPE = Configuration.getINSTANCE().getCompressType();

    @Override
    public void detect(final File input) {
        // check file
        checkFile(input);

        // set file name
        fileName = input.getName();

        // read file
        try {
            final byte[] plainFile = FileUtils.readFileToByteArray(input);
            fileSize = plainFile.length;
            comprFile = compress(plainFile);
        } catch (IOException e) {
            throw new RuntimeException(String.format("%s is not accessible.", fileName));
        }
        if (fileSize == 0 || fileSize > MAX_FILE_SIZE * 1024) {
            throw new RuntimeException(String.format(
                    "%s is too large. File size MUST less than %sKB.",
                    input.getName(), MAX_FILE_SIZE));
        }

        // set initialized at last.
        initialized = true;

    }

    /**
     * Compress.
     * 
     * @param plaintext the plaintext
     * @return the byte[]
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private byte[] compress(final byte[] plaintext) throws IOException {
        switch (COMPRESS_TYPE) {
        case snappy:
            return Snappy.compress(plaintext);
        default:
            return plaintext;
        }
    }

    /**
     * Check file.
     * 
     * @param input the input
     */
    private void checkFile(final File input) {
        if (!input.exists() || !input.isFile() || !input.canRead()) {
            throw new RuntimeException(String.format("%s is not accessible.", input.getName()));
        }
    }

    @Override
    public int getFileSize() {
        return fileSize;
    }

    @Override
    public int getSliceCount() {
        return (int) Math.ceil(comprFile.length * 1.0 / SLICE_SIZE);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public CompressType getCompressType() {
        return COMPRESS_TYPE;
    }

    @Override
    public int getCompressedSize() {
        return comprFile.length;
    }

    @Override
    public Iterator<SliceFrame> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub

    }

    /** The initialized. */
    private boolean initialized;

    /** The file size. */
    private int fileSize;

    /** The compr file. */
    private byte[] comprFile;

    /** The file name. */
    private String fileName;

}
