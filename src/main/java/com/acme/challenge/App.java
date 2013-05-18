package com.acme.challenge;


/**
 * 
 */
public class App {

	public static int MAX_QUEUE_SIZE = 300;
	
	public static void main(String[] args) {
		String path="e:\\downloads\\week_1.log";
		if (args.length > 0) {
		   if(args.length==1)
		   {
			   path=args[0];
		   }
		   else if(args.length==2)
		   {
			   path=args[0];
			   MAX_QUEUE_SIZE=Integer.parseInt(args[1]);
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
