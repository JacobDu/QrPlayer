package com.kula.qrplayer.gen;

/**
 * The Interface QrCodeGen.
 * Accept a file and generate a set of qrcode bitMatrixs.
 * 
 * @version 1.0
 */
public interface QrCodeGen {

	/**
	 * Pre-parse the input file, and build leading frame.
	 */
	void init();

	/**
	 * Generate.
	 */
	void generate();

	/**
	 * Do clean.
	 */
	void cleanup();

}