package com.kula.qrplayer.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * {@link BinaryQrCodeWriter} {@link BinaryQrCodeReader}
 */
public class BinaryQrCodeTest {

    /** The Constant BYTES. */
    final static byte[] BYTES = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /** The Constant SIZE. */
    final static int SIZE = 200;

    /** The Constant OUT_PATH. */
    final static File OUT_FOLDER = new File("./test/resource/");

    @Test
    public void test() throws WriterException, FileNotFoundException, IOException,
            NotFoundException, ChecksumException, FormatException {
        // encode
        final BinaryQrCodeWriter writer = new BinaryQrCodeWriter();
        final BitMatrix matrix = writer.encode(BYTES, SIZE, SIZE, ErrorCorrectionLevel.L);
        final File output = new File(OUT_FOLDER, "test.jpg");
        final OutputStream os = new FileOutputStream(output);
        MatrixToImageWriter.writeToStream(matrix, "jpg", os);
        os.flush();
        os.close();

        // decode
        final BufferedImage image = ImageIO.read(output);
        final LuminanceSource source = new BufferedImageLuminanceSource(image);
        final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        final BinaryQrCodeReader reader = new BinaryQrCodeReader();
        final byte[] result = reader.decode(bitmap);

        // verify & clean
        Assert.assertTrue(Arrays.equals(BYTES, result));
        Assert.assertTrue(output.delete());
    }
}
