package com.kula.qrplayer.entry;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.kula.qrplayer.gen.QrCodeGenerator;
import com.kula.qrplayer.interpret.QrCodeInterpreter;

/**
 * A factory for creating Engine objects.
 * 
 * @version 1.0
 */
public final class EngineFactory {

    /**
     * Creates a new Engine object.
     * 
     * @param parameters the parameters
     * @return the engine
     */
    public static Engine createGenerator(final Parameters parameters) {
        final QrCodeGenerator result = new QrCodeGenerator();
        // set input
        final File input = new File(parameters.getInput());
        checkFile(input);
        result.setInput(input);
        // set output
        final String outputDir = parameters.getOutput();
        if (StringUtils.isEmpty(outputDir)) {
            result.setOutputDir(input.getParentFile());
        } else {
            final File output = new File(outputDir);
            result.setOutputDir(output);
        }
        return result;
    }

    /**
     * Creates a new Engine object.
     * 
     * @param parameters the parameters
     * @return the engine
     */
    public static Engine createInterpretor(final Parameters parameters) {
        final QrCodeInterpreter result = new QrCodeInterpreter();
        final File inputDir = new File(parameters.getInput());
        final File output = new File(parameters.getOutput());
        checkInput(inputDir);
        result.setInputDir(inputDir);
        result.setOutput(output);
        return result;
    }

    /**
     * Check file.
     * 
     * @param file the input
     */
    private static void checkFile(final File file) {
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new RuntimeException(String.format("%s is not accessible.", file.getName()));
        }
    }

    /**
     * Check input.
     * 
     * @param input the input
     */
    private static void checkInput(final File input) {
        if (!input.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Input must be a directory."));
        }
        if (input.listFiles().length == 0) {
            throw new IllegalArgumentException(
                    String.format("Input must be a not empty directory."));
        }
    }
}
