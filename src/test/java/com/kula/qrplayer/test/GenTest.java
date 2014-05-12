package com.kula.qrplayer.test;

import org.junit.Test;

import com.kula.qrplayer.entry.Worker;

/**
 * The Class WorkerTest.
 * 
 * @version 1.0
 */
public class GenTest {

    /** The Constant INPUT_FILE. */
    public static final String INPUT_FILE = "./src/test/resource/telnet.xml";

    /** The Constant OUTPUT_DIR. */
    public static final String OUTPUT_DIR = "./path/to/";

    @Test
    public void test() {
        Worker.main(new String[] {
                "-m", "encode",
                "-i", INPUT_FILE,
                "-o", OUTPUT_DIR,
        });
    }
}
