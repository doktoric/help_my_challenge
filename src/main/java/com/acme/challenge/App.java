package com.acme.challenge;

import com.acme.challenge.model.MachineManager;
import com.acme.challenge.processor.JobProcessor;
import com.acme.challenge.simulation.SimulatedMachineManager;

/**
 * 
 */
public class App {

	public static void main(String[] args) {
		MachineManager machineManager = new MachineManager();
		SimulatedMachineManager simulatedMachineManager = new SimulatedMachineManager();
		JobProcessor jobProcessor = new JobProcessor(simulatedMachineManager, machineManager);
		LogParser parser = new LogParser(jobProcessor);
		// parser.start("d:\\projects\\prezi\\week_1.log");
		parser.start("e:\\downloads\\week_1.log");

		// new ExcelExporter().exportMapsToExcel(
		// machineManager.getUrlStatistics(),
		// machineManager.getGeneralStatistics(),
		// machineManager.getExportStatistics());
	}

}
