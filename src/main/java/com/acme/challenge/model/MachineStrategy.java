package com.acme.challenge.model;

import static com.acme.challenge.Helper.addDate;
import static com.acme.challenge.Helper.max;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.Job;
import com.acme.challenge.base.Machine;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquares;
import com.acme.challenge.simulation.SimulatedMachine;
import com.acme.challenge.simulation.SimulatedMachineManager;

public abstract class MachineStrategy {

	public static final double MAX_USAGE = 0.6;
	public static final double MIN_USAGE = 0.4;
	public static final int FORECAST_DISTANCE = 120;

	protected MachineManager machineManager;
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

		if (Math.ceil(getNrOfLaunchedVMs() * MAX_USAGE) <= max) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) max / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - getNrOfLaunchedVMs(); i++) {
				Date launchDate = addDate(now, 1);
				launchVM(launchDate);
			}
		}
		if (Math.ceil(getNrOfLaunchedVMs() * MIN_USAGE) >= max
				&& getNrOfLaunchedVMs() > 3) {
			int VMsNeeded = Math.max((int) Math.ceil((double) max / MAX_USAGE),
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
				statsQueue.size() + FORECAST_DISTANCE);
		// System.out.println("busy: " + manager.getBusyMachines(now) +
		// " predict: " + forecast + " launched: " + manager.launchedVMs);

		if (Math.ceil(getNrOfLaunchedVMs() * MAX_USAGE) <= forecast) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) forecast / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - getNrOfLaunchedVMs(); i++) {
				launchVM(addDate(now, 1));
			}
		}
		if (Math.ceil(getNrOfLaunchedVMs() * MIN_USAGE) >= forecast
				&& getNrOfLaunchedVMs() > 5) {
			int VMsNeeded = Math.max(
					(int) Math.ceil((double) forecast / MAX_USAGE), 5);
			for (int i = 0; i < getNrOfLaunchedVMs() - VMsNeeded; i++) {
				nominateToTermination(addDate(now, 1));
			}
		}
	}

	protected abstract int getNrOfLaunchedVMs();

	protected abstract void launchVM(Date date);

	protected abstract void nominateToTermination(Date date);

	protected abstract boolean isInTerminateTime(long diff);

}
