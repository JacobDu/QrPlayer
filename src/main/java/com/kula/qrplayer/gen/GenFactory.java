package com.kula.qrplayer.gen;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.kula.qrplayer.entry.Parameters;

/**
 * A factory for creating Gen objects.
 * 
 * @version 1.0
 */
public class GenFactory {

    /**
     * Creates a new Gen object.
     * 
     * @param parameters the parameters
     * @return the qr code gen
     */
    public static QrCodeGen createQrCodeGen(final Parameters parameters) {
        final QrCodeGenProvider result = new QrCodeGenProvider();
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
     * Check folder.
     * 
     * @param folder the folder
     */
    private static void checkFolder(final File folder) {
        if (!folder.exists()) {
            throw new RuntimeException(String.format("%s folder is not accessible.",
                    folder.getName()));
        }
    }

}
