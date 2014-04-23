package com.kula.qrplayer.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

/**
 * The Class IOUtil.
 * 
 * @version 1.0
 */
public class IOUtil {

	/** The Constant DEFAULT_CONFIG. */
	private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

	/**
	 * Writes a {@link BitMatrix} to a stream.
	 * 
	 * @see #toBufferedImage(BitMatrix)
	 */
	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
			throws IOException {
		writeToStream(matrix, format, stream, DEFAULT_CONFIG);
	}

	/**
	 * As {@link #writeToStream(BitMatrix, String, OutputStream)}, but allows customization of the
	 * output.
	 */
	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream,
			MatrixToImageConfig config) throws IOException {
		BufferedImage image = toBufferedImage(matrix, config);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	/**
	 * As {@link #toBufferedImage(BitMatrix)}, but allows customization of the output.
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix, MatrixToImageConfig config) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, config.getBufferedImageColorModel());
		int onColor = config.getPixelOnColor();
		int offColor = config.getPixelOffColor();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}
		}
		return image;
	}

	/**
	 * Instantiates a new iO util.
	 */
	private IOUtil() {
	}

}
