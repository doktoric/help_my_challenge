package com.acme.challenge.processor;

import static com.acme.challenge.model.ExportMachineStrategy.exportMachineStrategy;
import static com.acme.challenge.model.GeneralMachineStrategy.generalMachineStrategy;
import static com.acme.challenge.model.UrlMachineStrategy.urlMachineStrategy;
import static com.acme.challenge.model.manager.UrlMachineManager.urlMachineManager;
import static com.acme.challenge.model.manager.ExportMachineManager.exportMachineManager;
import static com.acme.challenge.model.manager.GeneralMachineManager.generalMachineManager;

import java.util.Date;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.model.MachineStrategy;
import com.acme.challenge.model.manager.MachineManager;
import com.acme.challenge.simulation.SimulatedMachineManager;

public class JobProcessor {
	
	private SimulatedMachineManager simulatedMachineManager;
	
	MachineStrategy urlMachineStrategy = urlMachineStrategy(urlMachineManager());
	MachineStrategy generalMachineStrategy = generalMachineStrategy(generalMachineManager());
	MachineStrategy exportMachineStrategy = exportMachineStrategy(exportMachineManager());
	
	private int processedJobs = 0;
	private Date currentBlock = null;

	public JobProcessor(SimulatedMachineManager simulatedMachineManager) {
		this.simulatedMachineManager = simulatedMachineManager;
	}


	public void processJob(Job job) {
		if (processedJobs == 0) {
			urlMachineStrategy.getMachineManager().launchMachine(job.getDateTime());
			exportMachineStrategy.getMachineManager().launchMachine(job.getDateTime());
			generalMachineStrategy.getMachineManager().launchMachine(job.getDateTime());
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
