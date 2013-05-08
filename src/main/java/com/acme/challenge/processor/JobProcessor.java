package com.acme.challenge.processor;

import static com.acme.challenge.model.ExportMachineStrategy.exportMachineStrategy;
import static com.acme.challenge.model.GeneralMachineStrategy.generalMachineStrategy;
import static com.acme.challenge.model.UrlMachineStrategy.urlMachineStrategy;

import java.util.Date;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.model.MachineManager;
import com.acme.challenge.model.MachineStrategy;
import com.acme.challenge.simulation.SimulatedMachineManager;

public class JobProcessor {
	
	private SimulatedMachineManager simulatedMachineManager;
	private MachineManager machineManager;
	
	MachineStrategy urlMachineStrategy = urlMachineStrategy(machineManager, QueueType.URL);
	MachineStrategy generalMachineStrategy = generalMachineStrategy(machineManager, QueueType.GENERAL);
	MachineStrategy exportMachineStrategy = exportMachineStrategy(machineManager, QueueType.EXPORT);
	
	private int processedJobs = 0;
	private Date currentBlock = null;

	public JobProcessor(SimulatedMachineManager simulatedMachineManager, MachineManager machineManager) {
		this.simulatedMachineManager = simulatedMachineManager;
		this.machineManager = machineManager;		
	}


	public void processJob(Job job) {
		if (processedJobs == 0) {
			machineManager.launchInitialMachines(job.getDateTime());
		}
		
		simulatedMachineManager.simulateVMLoad(job);
		
		if (currentBlock == null) {
			currentBlock = job.getDateTime();
		}
		// in case of new block (new second in logfile) create statistics
		if (currentBlock.getTime() != job.getDateTime().getTime()) {
			currentBlock = job.getDateTime();
			simulatedMachineManager.updateStatistics(currentBlock);
		}
		
		processStatistics();
		OutputWriter.writeJob(job.getJobOutput());
		processedJobs++;
	}


	private void processStatistics() {
		urlMachineStrategy.processStatisticsQueueVersion1(currentBlock, simulatedMachineManager.getUrlStatsQueue());
		generalMachineStrategy.processStatisticsQueueVersion1(currentBlock, simulatedMachineManager.getGeneralStatsQueue());
		exportMachineStrategy.processStatisticsQueueVersion1(currentBlock, simulatedMachineManager.getExportStatsQueue());
	}
}
