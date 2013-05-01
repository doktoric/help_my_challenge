package com.acme.challenge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

public class SimulatedMachineManager {

	private static final double MAX_USAGE = 0.6;
	private static final double MIN_USAGE = 0.4;

	private static final int MAX_QUEUE_SIZE = 60;

	private Date currentBlock = null;

	private List<SimulatedMachine> urlQMachines = new ArrayList<SimulatedMachine>();
	private List<SimulatedMachine> generalQMachines = new ArrayList<SimulatedMachine>();
	private List<SimulatedMachine> exportQMachines = new ArrayList<SimulatedMachine>();

	// private Map<Date, Integer> urlStatistics = new TreeMap<Date, Integer>();
	// private Map<Date, Integer> generalStatistics = new TreeMap<Date,
	// Integer>();
	// private Map<Date, Integer> exportStatistics = new TreeMap<Date,
	// Integer>();

	private PriorityQueue<UsageStatistics> urlStatsQueue = new PriorityQueue<>();
	private PriorityQueue<UsageStatistics> generalStatsQueue = new PriorityQueue<>();
	private PriorityQueue<UsageStatistics> exportStatsQueue = new PriorityQueue<>();
	private Integer launchedUrlVMs = 0;
	private Integer launchedGeneralVMs = 0;
	private Integer launchedExportVMs = 0;
//	private Integer runningVMs = 0;
	
	public void launchMachine(Date date, QueueType queueType){
		switch (queueType) {
		case URL:
			launchedUrlVMs++;
			break;
		case GENERAL:
			launchedGeneralVMs++;
			break;
		case EXPORT:
			launchedExportVMs++;
			break;
		default:
			break;
		}
		OutputWriter.writeVMCommand(date, Command.LAUNCH, queueType);
	}
	
	public void terminateMachine(Date date, QueueType queueType){
		switch (queueType) {
		case URL:
			launchedUrlVMs--;
			break;
		case GENERAL:
			launchedGeneralVMs--;
			break;
		case EXPORT:
			launchedExportVMs--;
			break;
		default:
			break;
		}
		OutputWriter.writeVMCommand(date, Command.TERMINATE, queueType);
	}

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
		// urlStatistics.put(currentBlock, getBusyMachines(now, urlQMachines));
		// generalStatistics.put(currentBlock, getBusyMachines(now,
		// generalQMachines));
		// exportStatistics.put(currentBlock, getBusyMachines(now,
		// exportQMachines));

		updateStatisticsQueue(now, QueueType.URL);
		updateStatisticsQueue(now, QueueType.GENERAL);
		updateStatisticsQueue(now, QueueType.EXPORT);
		
		processStatisticsQueue(now, QueueType.URL);
		processStatisticsQueue(now, QueueType.GENERAL);
		processStatisticsQueue(now, QueueType.EXPORT);
	}

	private void processStatisticsQueue(Date now, QueueType queueType) {
		PriorityQueue<UsageStatistics> statsQueue = null;
		int launchedVMs = 0;
		switch (queueType) {
		case URL:
			statsQueue = urlStatsQueue;
			launchedVMs = launchedUrlVMs;
			break;
		case GENERAL:
			statsQueue = generalStatsQueue;
			launchedVMs = launchedGeneralVMs;
			break;
		case EXPORT:
			statsQueue = exportStatsQueue;
			launchedVMs = launchedExportVMs;
			break;
		default:
			break;
		}
		
		int sum = 0;
		int max = 0;
		for (UsageStatistics stat : statsQueue) {
			sum += stat.getUsedVMs();
			if (stat.getUsedVMs() > max) {
				max = stat.getUsedVMs();
			}
		}
//		int avgUpperInt = (int) Math.ceil(sum / statsQueue.size());
//		System.out.println("avg: " + avgUpperInt + " max: " + max);
//		System.out.println("queue type " + queueType + " launched: " + launchedVMs + " max: " + max);
		if (Math.ceil(launchedVMs * MAX_USAGE) <= max){
			//launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double)max / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - launchedVMs; i++) {
				launchMachine(addDate(now, 1), queueType);
			}
		}
		if (Math.ceil(launchedVMs * MIN_USAGE) >= max && launchedVMs > 3){
			int VMsNeeded = Math.max((int) Math.ceil((double)max / MAX_USAGE), 3);
			for (int i = 0; i < launchedVMs - VMsNeeded; i++) {
				terminateMachine(addDate(now, 1), queueType);
			}
		}
	}

	private void updateStatisticsQueue(Date now, QueueType queueType) {
		PriorityQueue<UsageStatistics> statsQueue = null;
		List<SimulatedMachine> simulatedMachines = null;
		switch (queueType) {
		case URL:
			statsQueue = urlStatsQueue;
			simulatedMachines = urlQMachines;
			break;
		case GENERAL:
			statsQueue = generalStatsQueue;
			simulatedMachines = generalQMachines;
			break;
		case EXPORT:
			statsQueue = exportStatsQueue;
			simulatedMachines = exportQMachines;
			break;
		default:
			break;
		}
		statsQueue.add(new UsageStatistics(now, getBusyMachines(now, simulatedMachines)));
		if (statsQueue.size() > MAX_QUEUE_SIZE) {
			statsQueue.poll();
		}
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
		
		//simulate virtual machine usage for every queue
		if ("url".equals(job.getQueueType())) {
			simulateVMLoad(job, urlQMachines);
		} else if ("general".equals(job.getQueueType())) {
			simulateVMLoad(job, generalQMachines);
		} else if ("export".equals(job.getQueueType())) {
			simulateVMLoad(job, exportQMachines);
		}
		
		OutputWriter.writeJob(job.getJobOutput());
	}

	private void simulateVMLoad(Job job, List<SimulatedMachine> machines) {
		boolean foundAvailableMachine = false;
		for (SimulatedMachine machine : randomMachines(machines)) {
			if (!machine.isBusy(addDate(job.getDateTime(), 5d))) {
				machine.setBusyTill(addDate(
						max(machine.getBusyTill(), job.getDateTime()),
						job.getRuntimeInSeconds()));
				foundAvailableMachine = true;
				break;
			}
		}
		if (!foundAvailableMachine) {
			addMachine(
					new SimulatedMachine(job.getDateTime(), addDate(
							job.getDateTime(), job.getRuntimeInSeconds())),
					job.getQueueType());
		}
	}

	private List<SimulatedMachine> randomMachines(
			List<SimulatedMachine> machines) {
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

	// public Map<Date, Integer> getUrlStatistics() {
	// return urlStatistics;
	// }
	//
	// public Map<Date, Integer> getGeneralStatistics() {
	// return generalStatistics;
	// }
	//
	// public Map<Date, Integer> getExportStatistics() {
	// return exportStatistics;
	// }

}
