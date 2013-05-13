package com.acme.challenge.model;

import static com.acme.challenge.Helper.addDate;

import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.TmpFileWriter;
import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquares;
import com.acme.challenge.model.manager.MachineManager;
import com.acme.challenge.simulation.VirtualLoadSimulator;

public abstract class ScalingStrategy {

	protected MachineManager machineManager;
	protected LeastSquares leastSquares = LeastSquares.getInstance();

	public ScalingStrategy(MachineManager machineManager) {
		this.machineManager = machineManager;
	}

	public void processStatisticsQueueVersion0(Date now, PriorityQueue<UsageStatistics> statsQueue) {
		int max = 0;
		for (UsageStatistics stat : statsQueue) {
			if (stat.getUsedVMs() > max) {
				max = stat.getUsedVMs();
			}
		}

		if (Math.ceil(getNrOfLaunchedVMs() * getActualMaxUsage(now)) <= max) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) max / getActualMaxUsage(now));
			for (int i = 0; i < VMsNeeded - getNrOfLaunchedVMs(); i++) {
				Date launchDate = addDate(now, 1);
				launchVM(launchDate);
			}
		}
		if (Math.ceil(getNrOfLaunchedVMs() * getActualMinUsage(now)) >= max && getNrOfLaunchedVMs() > 3) {
			int VMsNeeded = Math.max((int) Math.ceil((double) max / getActualMaxUsage(now)), 3);
			int maxNrOfVMsToTerminate = getNrOfLaunchedVMs() - VMsNeeded;
			nominateToTermination(now, maxNrOfVMsToTerminate);
		}
	}

	public void processStatisticsQueueVersion1(Date now, PriorityQueue<UsageStatistics> statsQueue) {
		if (statsQueue.size() == VirtualLoadSimulator.MAX_QUEUE_SIZE) {
			SimpleRegression regression = leastSquares.createRegression(statsQueue);
			double forecast = leastSquares.getForecast(regression, statsQueue.size() + getActualForecastDistance(now));

			int VMsNeeded;
			if (Math.ceil(getNrOfLaunchedVMs() * getActualMaxUsage(now)) <= forecast) {
				VMsNeeded = (int) Math.ceil((double) forecast / getActualMaxUsage(now));
				for (int i = 0; i < VMsNeeded - getNrOfLaunchedVMs(); i++) {
					launchVM(now);
				}
			}
			if (Math.ceil(getNrOfLaunchedVMs() * getActualMinUsage(now)) >= forecast && getNrOfLaunchedVMs() > minimumMachineCount()) {
				VMsNeeded = Math.max((int) Math.ceil((double) forecast / getActualMaxUsage(now)), minimumMachineCount());
				int maxNrOfVMsToTerminate = getNrOfLaunchedVMs() - VMsNeeded;
				nominateToTermination(now, maxNrOfVMsToTerminate);
			}
			logForecast(now, getNrOfLaunchedVMs());
		} else {
			logForecast(now, 0);
		}
	}

	protected int getIndexToDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public abstract void logForecast(Date date, double forecast);

	public abstract void launchInitialMachines(Date date);

	protected abstract int getActualForecastDistance(Date date);

	protected abstract double getActualMinUsage(Date date);

	protected abstract double getActualMaxUsage(Date date);

	protected abstract int minimumMachineCount();

	protected abstract int getNrOfLaunchedVMs();

	protected abstract void launchVM(Date date);

	protected abstract void nominateToTermination(Date date, int maxNrToTerminate);

	protected abstract boolean isInTerminateTime(long diff);

}
