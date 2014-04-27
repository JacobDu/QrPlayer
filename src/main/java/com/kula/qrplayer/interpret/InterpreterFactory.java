package com.kula.qrplayer.interpret;

import java.io.File;

import com.kula.qrplayer.entry.Parameters;

/**
 * A factory for creating Interpreter objects.
 * 
 * @version 1.0
 */
public class InterpreterFactory {

    /**
     * Creates a new Interpreter object.
     * 
     * @param parameters the parameters
     * @return the qr code interpreter
     */
    public static QrCodeInterpreter createQrCodeInterpreter(final Parameters parameters) {
        final QrCodeInterpreterProvider result = new QrCodeInterpreterProvider();
        final File inputDir = new File(parameters.getInput());
        final File output = new File(parameters.getOutput());
        checkInput(inputDir);
        result.setInputDir(inputDir);
        result.setOutput(output);
        return result;
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
