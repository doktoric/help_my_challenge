package com.acme.challenge;

import static com.acme.challenge.model.MachineHolder.machineCollector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.acme.challenge.model.MachineHolder;

public class SimulatedMachineManager {

	public static final double MAX_USAGE = 0.6;
	public static final double MIN_USAGE = 0.4;
	public static final int MAX_QUEUE_SIZE = 60;

	private Date currentBlock = null;

	MachineHolder urlMachines = machineCollector(QueueType.URL);
	MachineHolder generalMachines = machineCollector(QueueType.GENERAL);
	MachineHolder exportMachines = machineCollector(QueueType.EXPORT);

	public void addMachine(SimulatedMachine machine, String type) {
		if (type.equals("url")) {
			urlMachines.addSimulatedMachine(machine);
		} else if (type.equals("general")) {
			generalMachines.addSimulatedMachine(machine);
		} else if (type.equals("export")) {
			exportMachines.addSimulatedMachine(machine);
		}
	}

	public void writeStatistics(Date now) {
		updateStatisticsQueue(now, QueueType.URL);
		updateStatisticsQueue(now, QueueType.GENERAL);
		updateStatisticsQueue(now, QueueType.EXPORT);

		processStatisticsQueue(now, QueueType.URL);
		processStatisticsQueue(now, QueueType.GENERAL);
		processStatisticsQueue(now, QueueType.EXPORT);
	}

	public void launchMachine(Date date,QueueType type) {
		switch (type) {
		case URL:
			urlMachines.launchMachine(date);
			break;
		case GENERAL:
			generalMachines.launchMachine(date);
			break;
		case EXPORT:
			exportMachines.launchMachine(date);
			break;
		default:
			break;
		}
		OutputWriter.writeVMCommand(date, Command.LAUNCH, type);
	}

	
	private void processStatisticsQueue(Date now, QueueType queueType) {

		switch (queueType) {
		case URL:
			urlMachines.processStatisticsQueue(now);
			break;
		case GENERAL:
			generalMachines.processStatisticsQueue(now);
			break;
		case EXPORT:
			exportMachines.processStatisticsQueue(now);
			break;
		default:
			break;
		}
	}

	private void updateStatisticsQueue(Date now, QueueType queueType) {

		switch (queueType) {
		case URL:
			urlMachines.updateStatisticQue(now);
			break;
		case GENERAL:
			generalMachines.updateStatisticQue(now);
			break;
		case EXPORT:
			exportMachines.updateStatisticQue(now);
			break;
		default:
			break;
		}

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

		// simulate virtual machine usage for every queue
		if ("url".equals(job.getQueueType())) {
			urlMachines.simulateVMLoad(job);
		} else if ("general".equals(job.getQueueType())) {
			generalMachines.simulateVMLoad(job);
		} else if ("export".equals(job.getQueueType())) {
			exportMachines.simulateVMLoad(job);
		}

		OutputWriter.writeJob(job.getJobOutput());
	}

}
