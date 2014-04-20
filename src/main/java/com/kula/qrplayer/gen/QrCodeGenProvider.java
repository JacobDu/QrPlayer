package com.kula.qrplayer.gen;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import com.kula.qrplayer.entity.LeadingFrame;
import com.kula.qrplayer.entity.SliceFrame;
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

    @Override
    public void init(final File input) {

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
        // TODO Auto-generated method stub

    }

    @Override
    public void cleanup() {
        leadingFrame = null;
        sliceFrames.clear();
        detector.cleanup();
    }

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
