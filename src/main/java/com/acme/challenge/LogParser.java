package com.acme.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;

public class LogParser {

    private SimulatedMachineManager machineManager;
    
    public LogParser(SimulatedMachineManager machineManager) {
        super();
        this.machineManager = machineManager;
    }

    public Job parseLine(String line) {
        String[] blocks = line.split(" ");
        Job job = new Job();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            job.setDateTime(dateFormat.parse(blocks[0] + " " + blocks[1]));
        } catch (ParseException e) {
            System.out.println("Cannot parse date!");
        }
        job.setId(blocks[2]);
        job.setQueueType(blocks[3]);
        job.setRuntimeInSeconds(Double.valueOf(blocks[4]));
        job.setJobOutput(line);
        return job;
    }

    public void start(String filePath) {
        File file = new File(filePath);
        Scanner input;
        try {
            input = new Scanner(file);
            int lineNumber = 0;
            while (input.hasNext()) {
                Job job = parseLine(input.nextLine());
                if (lineNumber==0){
                	for (int i = 0; i < 3; i++) {
                		machineManager.launchMachine(job.getDateTime(), QueueType.URL);
                		machineManager.launchMachine(job.getDateTime(), QueueType.GENERAL);
                		machineManager.launchMachine(job.getDateTime(), QueueType.EXPORT);
					}
                }
                lineNumber++;
                machineManager.processJob(job);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
