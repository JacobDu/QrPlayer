package com.kula.qrplayer.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kula.qrplayer.entity.LeadingFrame;
import com.kula.qrplayer.entity.SliceFrame;
import com.kula.qrplayer.qrcode.BinaryQrCodeWriter;
import com.kula.qrplayer.util.Configuration;

/**
 * The Class QrCodeGenProvider.
 * 
 * @version 1.0
 */
public class QrCodeGenProvider implements QrCodeGen {

	/** The Constant VERSION. */
	public static final int VERSION = 0x01;

	/** The Constant SLICE_DURATION. */
	public static final int SLICE_DURATION = Configuration.getINSTANCE().getSliceDuration();

	/** The Constant HEIGHT. */
	public static final int HEIGHT = Configuration.getINSTANCE().getOutputHeight();

	/** The Constant WIDTH. */
	public static final int WIDTH = Configuration.getINSTANCE().getOutputWidth();

	/** The Constant ERROR_CORRECTION_LEVEL. */
	public static final ErrorCorrectionLevel ERROR_CORRECTION_LEVEL = Configuration.getINSTANCE()
			.getECLevel();

	/** The Constant SUFFIX. */
	public static final String SUFFIX = Configuration.getINSTANCE().getPicFormat();

	@Override
	public void init() {

		// detect file
		detector.detect(input);
		// split file.
		for (SliceFrame frame : detector) {
			sliceFrames.add(frame);
		}
		// build leading frame
		leadingFrame = new LeadingFrame();
		leadingFrame.setVersion(VERSION);
		leadingFrame.setOrigFileName(detector.getFileName());
		leadingFrame.setOrigFileSize(detector.getFileSize());
		leadingFrame.setComprType(detector.getCompressType());
		leadingFrame.setSliceCount(detector.getSliceCount());
		leadingFrame.setCompressedSize(detector.getCompressedSize());
		// set duration
		leadingFrame.setSliceDuration(SLICE_DURATION);
	}

	@Override
	public void generate() {
		final BinaryQrCodeWriter writer = new BinaryQrCodeWriter();
		try {
			// gen leading frame
			final BitMatrix matrix = writer.encode(leadingFrame.toBytes(), WIDTH, HEIGHT,
					ERROR_CORRECTION_LEVEL);
			final File output = new File(outputDir, String.valueOf(System.nanoTime()) + "."
					+ SUFFIX);
			writeBitMatrixToFile(output, matrix, SUFFIX);
			// gen slice frame
			for (SliceFrame sliceFrame : sliceFrames) {
				final BitMatrix bitMatrix = writer.encode(sliceFrame.toBytes(), WIDTH, HEIGHT,
						ERROR_CORRECTION_LEVEL);
				final File outSlice = new File(outputDir, String.valueOf(System.nanoTime()) + "."
						+ SUFFIX);
				writeBitMatrixToFile(outSlice, bitMatrix, SUFFIX);
			}

		} catch (WriterException e) {
			throw new RuntimeException("Can not encode file.");
		}
	}

	/**
	 * Write bit matrix to file.
	 * 
	 * @param out the out
	 * @param matrix the matrix
	 * @param format the format
	 */
	private void writeBitMatrixToFile(final File out, final BitMatrix matrix, final String format) {
		try (final OutputStream os = new FileOutputStream(out);) {
			MatrixToImageWriter.writeToStream(matrix, format, os);
		} catch (IOException e) {
			throw new RuntimeException("Can not write to output dir");
		}
	}

	@Override
	public void cleanup() {
		leadingFrame = null;
		sliceFrames.clear();
		detector.cleanup();
	}

	public File getInput() {
		return input;
	}

	public void setInput(File input) {
		this.input = input;
	}

	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

	/** The input. */
	private File input;

	/** The output dir. */
	private File outputDir;

	/** The leading frame. */
	private LeadingFrame leadingFrame;

	/** The slice frames. */
	private Collection<SliceFrame> sliceFrames;

	/** The detector. */
	private FileDetectable detector;

	{
		sliceFrames = new LinkedList<SliceFrame>();
		detector = new FileDetector();
	}

}
