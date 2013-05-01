package com.acme.challenge;

import java.io.IOException;

/**
 * Hello world!
 * 
 */
public class App {

	/**
	 * @param args
	 * @throws IOException
	 * @throws WriteException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		SimulatedMachineManager machineManager = new SimulatedMachineManager();
		LogParser parser = new LogParser(machineManager);
		parser.start("e:\\prj\\python\\scale-contest-evaluator-master\\week_1.log");
//		new ExcelExporter().exportMapsToExcel(
//				machineManager.getUrlStatistics(),
//				machineManager.getGeneralStatistics(),
//				machineManager.getExportStatistics());
	}

}

