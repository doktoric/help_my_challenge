package com.acme.challenge.model;

import static com.acme.challenge.Helper.addDate;

import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.QueueType;
import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquares;
import com.acme.challenge.model.manager.MachineManager;

public abstract class MachineStrategy {


	protected MachineManager machineManager;

	public MachineManager getMachineManager() {
		return machineManager;
	}

	public void setMachineManager(MachineManager machineManager) {
		this.machineManager = machineManager;
	}

	protected LeastSquares leastSquares = new LeastSquares();
	protected QueueType type;

	public MachineStrategy(MachineManager machineManager, QueueType type) {
		this.type = type;
		this.machineManager = machineManager;
	}

	public void processStatisticsQueueVersion0(Date now,
			PriorityQueue<UsageStatistics> statsQueue) {
		int max = 0;
		for (UsageStatistics stat : statsQueue) {
			// sum += stat.getUsedVMs();
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
		if (Math.ceil(getNrOfLaunchedVMs() * getActualMinUsage(now)) >= max
				&& getNrOfLaunchedVMs() > 3) {
			int VMsNeeded = Math.max((int) Math.ceil((double) max / getActualMaxUsage(now)),
					3);
			for (int i = 0; i < getNrOfLaunchedVMs() - VMsNeeded; i++) {
				nominateToTermination(addDate(now, 1));
			}
		}
	}

	public void processStatisticsQueueVersion1(Date now,
			PriorityQueue<UsageStatistics> statsQueue) {
		SimpleRegression regression = leastSquares.createRegression(statsQueue);
		double forecast = leastSquares.getForecast(regression,
				statsQueue.size() + getActualForecastDistance(now));
		// System.out.println("busy: " + manager.getBusyMachines(now) +
		// " predict: " + forecast + " launched: " + manager.launchedVMs);

		if (Math.ceil(getNrOfLaunchedVMs() * getActualMaxUsage(now)) <= forecast) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) forecast / getActualMaxUsage(now));
			for (int i = 0; i < VMsNeeded - getNrOfLaunchedVMs(); i++) {
				launchVM(addDate(now, 1));
			}
		}
		if (Math.ceil(getNrOfLaunchedVMs() * getActualMinUsage(now)) >= forecast
				&& getNrOfLaunchedVMs() > 5) {
			int VMsNeeded = Math.max(
					////biztos hogy max kell ide????????
					(int) Math.ceil((double) forecast / getActualMaxUsage(now)), 5);
			for (int i = 0; i < getNrOfLaunchedVMs() - VMsNeeded; i++) {
				nominateToTermination(addDate(now, 1));
			}
		}
	}
	
	
	public int getIndexToDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	protected abstract int getActualForecastDistance(Date date);

	protected abstract double getActualMinUsage(Date date);

	protected abstract double getActualMaxUsage(Date date);

	protected abstract int getNrOfLaunchedVMs();

	protected abstract void launchVM(Date date);

	protected abstract void nominateToTermination(Date date);

	protected abstract boolean isInTerminateTime(long diff);

}
