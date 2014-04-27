package com.kula.qrplayer.entity;

/**
 * The Enum CompressType.
 * 
 * @version 1.0
 */
public enum CompressType {

    /** The gzip. */
    gzip((byte) 0x01),

    /** The snappy. */
    snappy((byte) 0x02),

    /** The zlib. */
    zlib((byte) 0x03),

    uncompress((byte) 0x00);

    private byte id;

    /**
     * Instantiates a new compress type.
     * 
     * @param aId the a id
     */
    private CompressType(final byte aId) {
        id = aId;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public byte getId() {
        return id;
    }

    /**
     * Gets the by id.
     * 
     * @param id the id
     * @return the by id
     */
    public static CompressType getById(final byte id) {
        switch (id) {
        case 0x01:
            return gzip;
        case 0x02:
            return snappy;
        case 0x03:
            return zlib;
        default:
            return uncompress;
        }
    }
}
