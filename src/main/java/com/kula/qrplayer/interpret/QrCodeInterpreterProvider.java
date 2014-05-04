package com.kula.qrplayer.interpret;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
import com.kula.qrplayer.entity.LeadingFrame;
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
        for (ByteBuffer buffer : entities) {
            buffer.mark();
            final byte[] magic = new byte[4];
            buffer.get(magic);
            buffer.reset();
            if (Arrays.equals(magic, LeadingFrame.MAGIC)) {
                leadingFrame = LeadingFrame.createLeadingFrame(buffer);
                entities.remove(buffer);
                return;
            }
        }
        throw new RuntimeException("Can not find leading frame.");
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
        checkLeadingFrame();
        final File outFile = new File(output, leadingFrame.getOrigFileName());

        final Map<Integer, byte[]> map = new HashMap<>();
        for (ByteBuffer buffer : entities) {
            final int index = buffer.getInt();
            final int remaining = buffer.remaining();
            final byte[] data = new byte[remaining];
            buffer.get(data);
            map.put(index, data);
        }

        try (final FileOutputStream stream = new FileOutputStream(outFile);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            for (int i = 0; i < leadingFrame.getSliceCount(); i++) {
                baos.write(map.get(i));
            }
            stream.write(baos.toByteArray());
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check leading frame.
     */
    private void checkLeadingFrame() {
        if (entities.size() != leadingFrame.getSliceCount()) {
            throw new RuntimeException("lack of slice.");
        }
    }

    @Override
    public void cleanup() {
        entities.clear();
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

    /** The leading frame. */
    private LeadingFrame leadingFrame;

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
