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

    /** The input. */
    private String input;

    /** The output. */
    private String output;

}
