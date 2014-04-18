package com.kula.qrplayer.qrcode;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.qrcode.QRCodeReader;

/**
 * The Class BinaryQrCodeReader.
 * 
 * @version 1.0
 */
public final class BinaryQrCodeReader {

    /**
     * Decode.
     *
     * @param image the image
     * @return the byte[]
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public byte[] decode(final BinaryBitmap image) throws NotFoundException, ChecksumException,
            FormatException {
        final QRCodeReader reader = new QRCodeReader();
        final Result result = reader.decode(image);
        return Base64.decodeBase64(result.getText());
    }
}
