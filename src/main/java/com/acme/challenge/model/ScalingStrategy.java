package com.acme.challenge.model;

import static com.acme.challenge.Helper.addDate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquares;
import com.acme.challenge.model.manager.MachineManager;

public abstract class ScalingStrategy {

	protected MachineManager machineManager;
	protected LeastSquares leastSquares = LeastSquares.getInstance();

	public ScalingStrategy(MachineManager machineManager) {
		this.machineManager = machineManager;
	}

	public void processStatisticsQueueVersion0(Date now, List<UsageStatistics> statsQueue) {
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

	public void processStatisticsQueueVersion1(Date now, List<UsageStatistics> statsQueue) {
		SimpleRegression regression = leastSquares.createRegression(statsQueue);
		double forecast = leastSquares.getForecast(regression, statsQueue.size() + getActualForecastDistance(now));

		// tells if we need upscale or downscale
		double slope = leastSquares.getSlope(regression);

		// upscale
		if (slope > 0) {
			// we predict the number of VMs needed 2 minutes from now based on
			// the simulation statistics
			// we want to have idle VMs because of the variance, so this
			// prediction is multiplied by a statistically stated value
			int VMsNeeded = (int) Math.ceil((double) forecast * getActualMaxUsage(now));
			for (int i = 0; i < VMsNeeded - getNrOfLaunchedVMs(); i++) {
				launchVM(now);
			}
		}
		// downscale
		if (slope < 0 && getNrOfLaunchedVMs() > minimumMachineCount()) {
			// it is possible that the slope is negative, but we don't need
			// upscale because it's degree is not high enough
			int VMsNeeded = Math.max((int) Math.ceil((double) forecast * getActualMaxUsage(now)), minimumMachineCount());
			if (getNrOfLaunchedVMs() > VMsNeeded) {
				// terminate max 1 machine per second
				Random random = new Random();
				double rnd = random.nextDouble();
				int maxNrOfVMsToTerminate;
				if (rnd < getChanceOfTermination()) {
					maxNrOfVMsToTerminate = 1;
				} else {
					maxNrOfVMsToTerminate = 0;
				}
				nominateToTermination(now, maxNrOfVMsToTerminate);
			}
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

	protected abstract double getChanceOfTermination();

}
