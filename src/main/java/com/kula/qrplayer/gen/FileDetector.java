package com.kula.qrplayer.gen;

import java.io.File;
import java.util.Iterator;

import com.kula.qrplayer.entity.CompressType;
import com.kula.qrplayer.entity.SliceFrame;

public class FileDetector implements FileDetectable {

    @Override
    public Iterator<SliceFrame> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void detect(File input) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getFileSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSliceCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getFileName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompressType getCompressType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCompressedSize() {
        // TODO Auto-generated method stub
        return 0;
    }

}
