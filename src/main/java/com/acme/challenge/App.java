package com.acme.challenge;

import com.acme.challenge.model.MachineManager;


/**
 * 
 */
public class App {

	public static void main(String[] args) {
		MachineManager machineManager = new MachineManager();
		LogParser parser = new LogParser(machineManager);
//		parser.start("d:\\projects\\prezi\\week_1.log");
		parser.start("e:\\downloads\\week_1.log");
		
		// new ExcelExporter().exportMapsToExcel(
		// machineManager.getUrlStatistics(),
		// machineManager.getGeneralStatistics(),
		// machineManager.getExportStatistics());
	}

}
