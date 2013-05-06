package com.acme.challenge;

import static com.acme.challenge.model.ExportMachineStrategy.exportMachineStrategy;
import static com.acme.challenge.model.GeneralMachineStrategy.generalMachineStrategy;
import static com.acme.challenge.model.UrlMachineStrategy.urlMachineStrategy;

import java.util.Date;

import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.model.MachineStrategy;

public class SimulatedMachineManager {

	private static final int INITIAL_MACHINE_COUNT = 3;

	private Date currentBlock = null;

	MachineStrategy urlMachineStrategy = urlMachineStrategy(QueueType.URL);
	MachineStrategy generalMachineStrategy = generalMachineStrategy(QueueType.GENERAL);
	MachineStrategy exportMachineStrategy = exportMachineStrategy(QueueType.EXPORT);

	public void writeStatistics(Date now) {
		updateStatisticsQueues(now);
		processStatisticsQueues(now);
	}

	private void processStatisticsQueues(Date now) {
		urlMachineStrategy.processStatisticsQueueVersion1(now);
		generalMachineStrategy.processStatisticsQueueVersion1(now);
		exportMachineStrategy.processStatisticsQueueVersion1(now);
	}

	private void updateStatisticsQueues(Date now) {
		urlMachineStrategy.updateStatisticQueue(now);
		generalMachineStrategy.updateStatisticQueue(now);
		exportMachineStrategy.updateStatisticQueue(now);

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
		switch (job.getQueueType()) {
		case URL:
			urlMachineStrategy.simulateVMLoad(job);
			break;
		case GENERAL:
			generalMachineStrategy.simulateVMLoad(job);
			break;
		case EXPORT:
			exportMachineStrategy.simulateVMLoad(job);
			break;
		}
		OutputWriter.writeJob(job.getJobOutput());
	}

	public void launchInitialMachines(Date dateTime) {
		for (int i = 0; i < INITIAL_MACHINE_COUNT; i++) {
			urlMachineStrategy.launchMachine(dateTime);
			generalMachineStrategy.launchMachine(dateTime);
			exportMachineStrategy.launchMachine(dateTime);
		}
	}

}
