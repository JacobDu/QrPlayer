package com.kula.qrplayer.test;

import org.junit.Test;

import com.kula.qrplayer.entry.Worker;

public class InterpretorTest {

    /** The Constant INPUT_FILE. */
    public static final String INPUT_FILE = "./src/test/resource/qrCodePics/";

    /** The Constant OUTPUT_DIR. */
    public static final String OUTPUT_DIR = "./path/to/";

    @Test
    public void test() {
        Worker.main(new String[] {
                "-m", "decode",
                "-i", INPUT_FILE,
                "-o", OUTPUT_DIR,
        });
    }
}
