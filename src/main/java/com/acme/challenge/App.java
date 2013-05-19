package com.acme.challenge;

import com.acme.challenge.simulation.VirtualLoadSimulator;


/**
 * 
 */
public class App {

	public static void main(String[] args) {
		String path="e:\\downloads\\week_1.log";
		if (args.length > 0) {
		   if(args.length==1)
		   {
			   path=args[0];
		   }
		   else if(args.length==4)
		   {
			   path=args[0];
			   VirtualLoadSimulator.URL_MAX_QUEUE_SIZE=Integer.parseInt(args[1]);
			   VirtualLoadSimulator.GENERAL_MAX_QUEUE_SIZE=Integer.parseInt(args[2]);
			   VirtualLoadSimulator.EXPORT_MAX_QUEUE_SIZE=Integer.parseInt(args[3]);
		   }
		}
		
		LogParser parser = LogParser.getInstance();
		parser.start(path);

		// new ExcelExporter().exportMapsToExcel(
		// machineManager.getUrlStatistics(),
		// machineManager.getGeneralStatistics(),
		// machineManager.getExportStatistics());
	}

}
