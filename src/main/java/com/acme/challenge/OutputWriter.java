package com.acme.challenge;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputWriter {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void writeJob(String line){
		//System.out.println(line);
	}
	
	public static void writeVMCommand(Date date, Command command, QueueType queueType){
		//System.out.println(dateFormat.format(date) + " " + command.toString().toLowerCase() + " " + queueType.toString().toLowerCase());
	}

}
