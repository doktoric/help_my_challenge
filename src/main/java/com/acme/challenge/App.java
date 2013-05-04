package com.acme.challenge;


/**
 * Hello world!
 * 
 */
public class App {

//	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
//		logger.info("sfdsffsdf-------------------");
		SimulatedMachineManager machineManager = new SimulatedMachineManager();
		LogParser parser = new LogParser(machineManager);
		parser.start("d:\\projects\\prezi\\week_1.log");
		
		// new ExcelExporter().exportMapsToExcel(
		// machineManager.getUrlStatistics(),
		// machineManager.getGeneralStatistics(),
		// machineManager.getExportStatistics());
	}

}
