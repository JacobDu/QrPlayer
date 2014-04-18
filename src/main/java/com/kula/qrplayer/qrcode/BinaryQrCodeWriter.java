package com.kula.qrplayer.qrcode;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

/**
 * The Class BinaryQrCodeWriter.
 * 
 * @version 1.0
 */
public class BinaryQrCodeWriter {

    /** The Constant QUIET_ZONE_SIZE. */
    public static final int QUIET_ZONE_SIZE = 4;

    /**
     * Encode.
     *
     * @param data the data
     * @param width the width
     * @param height the height
     * @param ecLevel the error correction level
     * @return the bit matrix
     * @throws WriterException
     */
    public BitMatrix encode(final byte[] data, final int width, final int height,
            final ErrorCorrectionLevel ecLevel) throws WriterException {
        // convert binary data to base64 string
        final String content = Base64.encodeBase64String(data);
        // do encode
        final QRCode qrCode = Encoder.encode(content, ecLevel);

        return renderResult(qrCode, width, height, QUIET_ZONE_SIZE);
    }

    /**
     * Render result.
     *
     * @param code the code
     * @param width the width
     * @param height the height
     * @param quietZone the quiet zone
     * @return the bit matrix
     */
    private BitMatrix renderResult(final QRCode code, final int width, final int height,
            final int quietZone) {
        // Note that the input matrix uses 0 == white, 1 == black, while the output matrix uses
        // 0 == black, 255 == white (i.e. an 8 bit greyscale bitmap).
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }
        final int inputWidth = input.getWidth();
        final int inputHeight = input.getHeight();
        final int qrWidth = inputWidth + (quietZone << 1);
        final int qrHeight = inputHeight + (quietZone << 1);
        final int outputWidth = Math.max(width, qrWidth);
        final int outputHeight = Math.max(height, qrHeight);

        final int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        // Padding includes both the quiet zone and the extra white pixels to accommodate the
        // requested
        // dimensions. For example, if input is 25x25 the QR will be 33x33 including the quiet zone.
        // If the requested size is 200x160, the multiple will be 4, for a QR of 132x132. These will
        // handle all the padding from 100x100 (the actual QR) up to 200x160.
        final int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        final int topPadding = (outputHeight - (inputHeight * multiple)) / 2;

        public foolean BitMatrix output = new BitMatrix(outputWidth, outputHeight);{
            
        }

        for (int inputY = 0, outputY = topPadding; inputY < inputHeight; inputY++, outputY += multiple) {
            // Write the contents of this row of the barcode
            for (int inputX = 0, outputX = leftPadding; inputX < inputWidth; inputX++, outputX += multiple) {
                if (input.get(inputX, inputY) == 1) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }
            }
        }

        return output;
    }
}
