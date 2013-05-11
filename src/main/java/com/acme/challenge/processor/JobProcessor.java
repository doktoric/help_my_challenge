package com.acme.challenge.processor;

import java.util.Date;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Job;
import com.acme.challenge.model.ExportQueueScalingStrategy;
import com.acme.challenge.model.GeneralQueueScalingStrategy;
import com.acme.challenge.model.ScalingStrategy;
import com.acme.challenge.model.UrlQueueScalingStrategy;
import com.acme.challenge.simulation.VirtualLoadSimulator;

public class JobProcessor {
	
	private JobProcessor(){}
	
	private static class JobProcessorHolder { 
        public static final JobProcessor INSTANCE = new JobProcessor();
	}
	
	public static JobProcessor getInstance() {
        return JobProcessorHolder.INSTANCE;
	}
	
	ScalingStrategy urlMachineStrategy = UrlQueueScalingStrategy.getInstance();
	ScalingStrategy generalMachineStrategy = GeneralQueueScalingStrategy.getInstance();
	ScalingStrategy exportMachineStrategy = ExportQueueScalingStrategy.getInstance();
	
	private Date currentSecond = null;

	public void processJob(Job job) {
		VirtualLoadSimulator simulatedMachineManager = VirtualLoadSimulator.getInstance();
		
		if (firstJobToProcess()) {
			launchInitialMachines(job);
			currentSecond = job.getDateTime();
		}
		
		simulatedMachineManager.simulateVMLoad(job);
			
		if (parsedEveryJobInPreviousSecond(job)) {
			simulatedMachineManager.updateStatistics(currentSecond);
			currentSecond = job.getDateTime();
			processStatistics(simulatedMachineManager);
		}
		
		OutputWriter.writeJob(job.getJobOutput());
	}


	private boolean parsedEveryJobInPreviousSecond(Job job) {
		return currentSecond.getTime() != job.getDateTime().getTime();
	}

	private boolean firstJobToProcess() {
		return currentSecond == null;
	}

	private void launchInitialMachines(Job job) {
		urlMachineStrategy.launchInitialMachines(job.getDateTime());
		exportMachineStrategy.launchInitialMachines(job.getDateTime());
		generalMachineStrategy.launchInitialMachines(job.getDateTime());
	}

	private void processStatistics(VirtualLoadSimulator simulatedMachineManager) {
		urlMachineStrategy.processStatisticsQueueVersion1(currentSecond, simulatedMachineManager.getUrlStatsQueue());
		generalMachineStrategy.processStatisticsQueueVersion1(currentSecond, simulatedMachineManager.getGeneralStatsQueue());
		exportMachineStrategy.processStatisticsQueueVersion1(currentSecond, simulatedMachineManager.getExportStatsQueue());
	}
}
