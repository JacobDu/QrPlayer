package com.kula.qrplayer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kula.qrplayer.entity.CompressType;

/**
 * The Class Configuration.
 * 
 * @version 1.0
 */
public final class Configuration {

	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static Configuration getINSTANCE() {
		return INSTANCE;
	}

	/**
	 * Gets the max file size. Default is 1024KB. Unit is KB.
	 * 
	 * @return the max file size
	 */
	public int getMaxFileSize() {
		return getInt("max_file_size", "1024");
	}

	/**
	 * Gets the slice size. Default is 3000Byte. Unit is Byte.
	 * 
	 * @return the slice size
	 */
	public int getSliceSize() {
		return getInt("slice_size", "3000");
	}

	/**
	 * Gets the slice duration.
	 * 
	 * @return the slice duration
	 */
	public int getSliceDuration() {
		return getInt("slice_duration", "200");
	}

	/**
	 * Gets the compress type.
	 * 
	 * @return the compress type
	 */
	public CompressType getCompressType() {
		return CompressType.valueOf(properties.getProperty("compress_type", "snappy"));
	}

	/**
	 * Gets the output height.
	 * 
	 * @return the output height
	 */
	public int getOutputHeight() {
		return getInt("output_height", "400");
	}

	/**
	 * Gets the eC level.
	 * 
	 * @return the eC level
	 */
	public ErrorCorrectionLevel getECLevel() {
		return ErrorCorrectionLevel.valueOf(properties.getProperty("error_correction_level", "L"));
	}

	/**
	 * Gets the output width.
	 * 
	 * @return the output width
	 */
	public int getOutputWidth() {
		return getInt("output_width", "400");
	}

	/**
	 * Gets the pic format.
	 * 
	 * @return the pic format
	 */
	public String getPicFormat() {
		return properties.getProperty("pic_format", "png");
	}

	/**
	 * Gets the int.
	 * 
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the int
	 */
	private int getInt(final String key, final String defaultValue) {
		return Integer.valueOf(properties.getProperty(key, defaultValue));
	}

	/**
	 * Gets the int.
	 * 
	 * @param key the key
	 * @return the int
	 */
	private int getInt(final String key) {
		return Integer.valueOf(properties.getProperty(key));
	}

	/**
	 * Instantiates a new configuration.
	 */
	private Configuration() {
		properties = new Properties();
		try (InputStream config = Configuration.class.getClassLoader().getResourceAsStream(
				CONFIG_NAME)) {
			properties.load(config);

		} catch (IOException e) {
			throw new RuntimeException("qrplayer.properties is not accessible.");
		}

	}

	/** The Constant CONFIG_NAME. */
	private static final String CONFIG_NAME = "qrplayer.properties";

	/** The Constant INSTANCE. */
	private static final Configuration INSTANCE = new Configuration();

	/** The properties. */
	private final Properties properties;
}
