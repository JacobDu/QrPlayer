package com.kula.qrplayer.gen;

import java.io.File;

/**
 * The Interface QrCodeGen.
 * Accept a file and generate a set of qrcode bitMatrixs.
 * 
 * @version 1.0
 */
public interface QrCodeGen {

    /**
     * Pre-parse the input file, and build leading frame.
     * 
     * @param input the input
     */
    void init(File input);

    /**
     * Generate.
     */
    void generate();

    /**
     * Do clean.
     */
    void doClean();

}