package com.acme.challenge;

import java.io.IOException;

import jxl.write.WriteException;

/**
 * Hello world!
 * 
 */
public class App {

	/**
	 * @param args
	 * @throws IOException
	 * @throws WriteException
	 */
	public static void main(String[] args) throws WriteException, IOException {
		SimulatedMachineManager machineManager = new SimulatedMachineManager();
		LogParser parser = new LogParser(machineManager);
		// parser.start("d:\\prj\\help_my_challenge\\week_1.log");
		parser.start("d:\\prj\\help_my_challenge\\week_1.log");
		new ExcelExporter().exportMapsToExcel(
				machineManager.getUrlStatistics(),
				machineManager.getGeneralStatistics(),
				machineManager.getExportStatistics());
	}

}
