package com.kula.qrplayer.entry;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Command.
 * 
 * @version 1.0
 */
public final class Command {

    /**
     * The Enum Cmd.
     */
    enum Cmd {

        /** The mode. */
        mode("m"),

        /** The input. */
        input("i"),

        /** The output. */
        output("o"),

        /** The version. */
        version("v"),

        /** The help. */
        help("h");

        /** The abbr. */
        private String abbr;

        /**
         * Instantiates a new cmd.
         * 
         * @param abbr the abbr
         */
        Cmd(final String abbr) {
            this.abbr = abbr;
        }

        /**
         * Gets the abbr.
         * 
         * @return the abbr
         */
        String getAbbr() {
            return abbr;
        }
    }

    /**
     * Inits the option.
     */
    public void initOption() {
        // i, input
        options.addOption(Cmd.input.getAbbr(), Cmd.input.name(), true,
                "Input file path. This option is required.");
        // o, output
        options.addOption(Cmd.output.getAbbr(), Cmd.output.name(), true,
                "Output folder. This option is optional. Default for input folder.");
        // m, mode
        options.addOption(Cmd.mode.getAbbr(), Cmd.mode.name(), true,
                "Engine mode. 'encode' for generate qrCode. 'decode' for convert qrCode to source file.");

        // v, version
        options.addOption(Cmd.version.getAbbr(), Cmd.version.name(), false, "display version.");
        // h, help
        options.addOption(Cmd.help.getAbbr(), Cmd.help.name(), false, "dispaly help.");
    }

    /**
     * Parses the.
     * 
     * @param args the args
     * @return the parameters
     * @throws ParseException
     */
    public Parameters parse(final String[] args) throws ParseException {
        final CommandLineParser parser = new PosixParser();
        final CommandLine cmdLine = parser.parse(options, args);
        final ParametersData parameters = new ParametersData();

        // check cmdLine
        checkCmd(cmdLine);

        // build parameter
        parameters.setInput(cmdLine.getOptionValue(Cmd.input.name()));
        parameters.setOutput(cmdLine.getOptionValue(Cmd.output.name(), StringUtils.EMPTY));
        parameters.setMode(cmdLine.getOptionValue(Cmd.mode.name()));

        return parameters;
    }

    /**
     * Check cmd.
     * 
     * @param cmdLine the cmd line
     */
    private void checkCmd(CommandLine cmdLine) {
        if (!cmdLine.hasOption("i")) {
            throw new IllegalArgumentException("Input option is miss.");
        }
    }

    /** The options. */
    private Options options;

    {
        options = new Options();
    }
}
