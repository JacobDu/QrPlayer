package com.kula.qrplayer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        try (InputStream config = Configuration.class.getClassLoader()
                .getResourceAsStream(CONFIG_NAME)) {
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
