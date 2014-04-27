package com.kula.qrplayer.interpret;

/**
 * The Interface QrCode Interpreter.
 * 
 * @version 1.0
 */
public interface QrCodeInterpreter {

    /**
     * Inits the.
     */
    public void init();

    /**
     * Interpret.
     */
    public void interpret();

    /**
     * Cleanup.
     */
    public void cleanup();

}
