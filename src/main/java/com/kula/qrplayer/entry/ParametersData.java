package com.kula.qrplayer.entry;

/**
 * The Class ParametersData.
 */
public final class ParametersData implements Parameters {

    @Override
    public String getInput() {
        return input;
    }

    /**
     * Sets the input.
     * 
     * @param input the new input
     */
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String getOutput() {
        return output;
    }

    /**
     * Sets the output.
     * 
     * @param output the new output
     */
    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String getMode() {
        return mode;
    }

    /**
     * Sets the mode.
     * 
     * @param mode the new mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /** The input. */
    private String input;

    /** The output. */
    private String output;

    /** The mode. */
    private String mode;

}
