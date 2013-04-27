package com.acme.challenge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class SimulatedMachineManager {

    private Date currentBlock = null;

    private List<SimulatedMachine> urlQMachines = new ArrayList<SimulatedMachine>();
    private List<SimulatedMachine> generalQMachines = new ArrayList<SimulatedMachine>();
    private List<SimulatedMachine> exportQMachines = new ArrayList<SimulatedMachine>();

    private Map<Date, Integer> urlStatistics = new TreeMap<Date, Integer>();
    private Map<Date, Integer> generalStatistics = new TreeMap<Date, Integer>();
    private Map<Date, Integer> exportStatistics = new TreeMap<Date, Integer>();

    public void addMachine(SimulatedMachine machine, String type) {
        if (type.equals("url")) {
            urlQMachines.add(machine);
        } else if (type.equals("general")) {
            generalQMachines.add(machine);
        } else if (type.equals("export")) {
            exportQMachines.add(machine);
        }
    }

    public void writeStatistics(Date now) {
        urlStatistics.put(currentBlock, getBusyMachines(now, urlQMachines));
        generalStatistics.put(currentBlock, getBusyMachines(now, generalQMachines));
        exportStatistics.put(currentBlock, getBusyMachines(now, exportQMachines));
    }

    private Integer getBusyMachines(Date now, List<SimulatedMachine> machines) {
        Integer ret = 0;
        for (SimulatedMachine machine : machines) {
            if (machine.isBusy(now)) {
                ret++;
            }
        }
        return ret;
    }

    public void processJob(Job job) {
        if (currentBlock == null) {
            currentBlock = job.getDateTime();
        }
        // in case of new block (new second in logfile) create statistics
        if (currentBlock.getTime() != job.getDateTime().getTime()) {
            writeStatistics(currentBlock);
            currentBlock = job.getDateTime();
        }
        if ("url".equals(job.getQueueType())) {
            simulateVMLoad(job, urlQMachines);
        } else if ("general".equals(job.getQueueType())) {
            simulateVMLoad(job, generalQMachines);
        } else if ("export".equals(job.getQueueType())) {
            simulateVMLoad(job, exportQMachines);
        }
    }

    private void simulateVMLoad(Job job, List<SimulatedMachine> machines) {
        boolean foundAvailableMachine = false;
        for (SimulatedMachine machine : randomMachines(machines)) {
            if (!machine.isBusy(addDate(job.getDateTime(), 5d))) {
                machine.setBusyTill(addDate(max(machine.getBusyTill(), job.getDateTime()), job.getRuntimeInSeconds()));
                foundAvailableMachine = true;
                break;
            }
        }
        if (!foundAvailableMachine) {
            addMachine(new SimulatedMachine(job.getDateTime(), addDate(job.getDateTime(), job.getRuntimeInSeconds())), job.getQueueType());
        }
    }

    private List<SimulatedMachine> randomMachines(List<SimulatedMachine> machines) {
        List<SimulatedMachine> ret = new ArrayList<SimulatedMachine>();
        if (machines.size() > 0) {
            int size = machines.size();
            Random rnd = new Random();
            int randomStart = rnd.nextInt(size);
            for (int i = 0; i < size; i++) {
                ret.add(machines.get((randomStart + i) % size));
            }
        }
        return ret;
    }

    private Date max(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return date1;
        } else {
            return date2;
        }
    }

    private Date addDate(Date date, double seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MILLISECOND, (int) (seconds * 1000));
        return cal.getTime();
    }

    public Map<Date, Integer> getUrlStatistics() {
        return urlStatistics;
    }

    public Map<Date, Integer> getGeneralStatistics() {
        return generalStatistics;
    }

    public Map<Date, Integer> getExportStatistics() {
        return exportStatistics;
    }

}
