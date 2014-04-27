package com.kula.qrplayer.entry;

import org.apache.commons.cli.ParseException;

import com.kula.qrplayer.gen.GenFactory;
import com.kula.qrplayer.gen.QrCodeGen;

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
			final QrCodeGen qrCodeGen = GenFactory.createQrCodeGen(parameters);
			qrCodeGen.init();
			qrCodeGen.generate();
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
