package com.acme.challenge;

import static com.acme.challenge.model.GeneralMachineStrategy.generalMachineStrategy;
import static com.acme.challenge.model.UrlMachineStrategy.urlMachineStrategy;
import static com.acme.challenge.model.ExportMachineStrategy.exportMachineStrategy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.acme.challenge.base.Command;
import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.model.MachineStrategy;
import com.acme.challenge.model.SimulatedMachine;

public class SimulatedMachineManager {

	public static final double MAX_USAGE = 0.6;
	public static final double MIN_USAGE = 0.4;
	public static final int MAX_QUEUE_SIZE = 3600;

	private Date currentBlock = null;

	MachineStrategy urlMachines = urlMachineStrategy(QueueType.URL);
	MachineStrategy generalMachines = generalMachineStrategy(QueueType.GENERAL);
	MachineStrategy exportMachines = exportMachineStrategy(QueueType.EXPORT);

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
		updateStatisticsQueue(now);

		processStatisticsQueue(now);
	}

	public void launchMachine(Date date, QueueType type) {
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

	private void processStatisticsQueue(Date now) {

		urlMachines.processStatisticsQueue(now);
		generalMachines.processStatisticsQueue(now);
		exportMachines.processStatisticsQueue(now);
	}

	private void updateStatisticsQueue(Date now) {

		urlMachines.updateStatisticQue(now);
		generalMachines.updateStatisticQue(now);
		exportMachines.updateStatisticQue(now);

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
