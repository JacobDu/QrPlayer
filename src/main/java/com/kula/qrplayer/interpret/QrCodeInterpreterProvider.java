package com.kula.qrplayer.interpret;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
<<<<<<< HEAD
import java.util.Arrays;
=======
>>>>>>> c0cdea07c673962c2f12c1e50e37ab092b0e9a3d
import java.util.Collection;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
<<<<<<< HEAD
import com.kula.qrplayer.entity.LeadingFrame;
=======
>>>>>>> c0cdea07c673962c2f12c1e50e37ab092b0e9a3d
import com.kula.qrplayer.qrcode.BinaryQrCodeReader;
import com.kula.qrplayer.util.Configuration;

/**
 * The Class QrCodeInterpreterProvider.
 * 
 * @version 1.0
 */
public class QrCodeInterpreterProvider implements QrCodeInterpreter {

    /** The Constant SUFFIX. */
    public static final String SUFFIX = Configuration.getINSTANCE().getPicFormat();

    @Override
    public void init() {
        // load all possible pictures
        loadPictures(inputDir);
        // find and parse leading frame
        findOutLeadingFrame();
    }

    /**
     * Find out leading frame.
     */
    private void findOutLeadingFrame() {
<<<<<<< HEAD
        for (ByteBuffer buffer : entities) {
            final byte[] magic = new byte[4];
            buffer.get(magic);
            if (Arrays.equals(magic, LeadingFrame.MAGIC)) {
                leadingFrame = new LeadingFrame();
                return;
            }
        }
        throw new RuntimeException("Can not find leading frame.");
=======
        // TODO Auto-generated method stub
>>>>>>> c0cdea07c673962c2f12c1e50e37ab092b0e9a3d
    }

    /**
     * Load pictures.
     * 
     * @param aInputDir the a input dir
     */
    private void loadPictures(final File aInputDir) {
        final IOFileFilter fileFilter = FileFilterUtils
                .suffixFileFilter(SUFFIX, IOCase.INSENSITIVE);
        final Collection<File> pictures = FileUtils.listFiles(aInputDir, fileFilter, null);
        for (File file : pictures) {
            try {
                final BufferedImage image = ImageIO.read(output);
                final LuminanceSource source = new BufferedImageLuminanceSource(image);
                final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                final byte[] result = reader.decode(bitmap);
                entities.add(ByteBuffer.wrap(result));
            } catch (IOException | NotFoundException | ChecksumException | FormatException e) {
                System.err.println(String.format("%s can not be load as a qrCode. Cause: %s",
                        file.getName(), e.getMessage()));
            }

        }
    }

    @Override
    public void interpret() {
        // TODO Auto-generated method stub

    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub

    }

    /**
     * Gets the input dir.
     * 
     * @return the input dir
     */
    public File getInputDir() {
        return inputDir;
    }

    /**
     * Gets the output.
     * 
     * @return the output
     */
    public File getOutput() {
        return output;
    }

    /**
     * Sets the output.
     * 
     * @param output the new output
     */
    public void setOutput(File output) {
        this.output = output;
    }

    /**
     * Sets the input dir.
     * 
     * @param inputDir the new input dir
     */
    public void setInputDir(File inputDir) {
        this.inputDir = inputDir;
    }

<<<<<<< HEAD
    /** The leading frame. */
    private LeadingFrame leadingFrame;

=======
>>>>>>> c0cdea07c673962c2f12c1e50e37ab092b0e9a3d
    /** The input dir. */
    private File inputDir;

    /** The output. */
    private File output;

    private BinaryQrCodeReader reader;

    /** The entities. */
    private Collection<ByteBuffer> entities;

    {
        entities = new LinkedList<ByteBuffer>();
        reader = new BinaryQrCodeReader();
    }

}
