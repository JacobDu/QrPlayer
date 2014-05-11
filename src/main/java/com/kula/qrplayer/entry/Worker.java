package com.kula.qrplayer.entry;

import org.apache.commons.cli.ParseException;

/**
 * The Class Worker.
 */
public class Worker {

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(final String[] args) {
        final Command command = new Command();
        command.initOption();
        try {
            final Parameters parameters = command.parse(args);
            if (parameters != null) {
                Mode mode = Mode.valueOf(parameters.getMode());
                Engine engine = null;
                if (mode == Mode.encode) {
                    engine = EngineFactory.createGenerator(parameters);
                } else if (mode == Mode.decode) {
                    engine = EngineFactory.createInterpretor(parameters);
                }
                if (engine != null) {
                    engine.prepare();
                    engine.fire();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new worker.
     */
    private Worker() {

    }
}
