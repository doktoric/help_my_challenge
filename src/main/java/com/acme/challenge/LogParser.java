package com.acme.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.processor.JobProcessor;

public class LogParser {

	private LogParser(){}
	
	private static class LogParserHolder { 
        public static final LogParser INSTANCE = new LogParser();
	}
	
	public static LogParser getInstance() {
        return LogParserHolder.INSTANCE;
	}

	public Job parseLine(String line) {
		String[] blocks = line.split(" ");
		Job job = new Job();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			job.setDateTime(dateFormat.parse(blocks[0] + " " + blocks[1]));
		} catch (ParseException e) {
			System.out.println("Cannot parse date!");
		}
		job.setId(blocks[2]);
		if ("url".equals(blocks[3])) {
			job.setQueueType(QueueType.URL);
		} else if ("general".equals(blocks[3])) {
			job.setQueueType(QueueType.GENERAL);
		} else if ("export".equals(blocks[3])) {
			job.setQueueType(QueueType.EXPORT);
		}
		
		job.setRuntimeInSeconds(Double.valueOf(blocks[4]));
		job.setJobOutput(line);
		return job;
	}

	public void start(String filePath) {
		File file = new File(filePath);
		Scanner input;
		try {
			input = new Scanner(file);
			while (input.hasNext()) {
				Job job = parseLine(input.nextLine());
				JobProcessor.getInstance().processJob(job);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
