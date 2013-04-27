package com.acme.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

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
        return job;
    }

    public void start(String filePath) {
        File file = new File(filePath);
        Scanner input;
        try {
            input = new Scanner(file);
            while (input.hasNext()) {
                Job job = parseLine(input.nextLine());
                machineManager.processJob(job);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
