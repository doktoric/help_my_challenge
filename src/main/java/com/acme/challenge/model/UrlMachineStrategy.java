package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.base.QueueType;
import com.acme.challenge.model.manager.MachineManager;

public class UrlMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 10000;

	public UrlMachineStrategy(MachineManager machineManager, QueueType type) {
		super(machineManager, type);
	}

	public static MachineStrategy urlMachineStrategy(
			MachineManager machineManager) {
		return new UrlMachineStrategy(machineManager, QueueType.URL);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated = false;
		isTerminated = (diff < END_OF_HOUR);
		return isTerminated;
	}

	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchMachine(date);
	}

	@Override
	protected void nominateToTermination(Date date) {
		machineManager.terminateIfNearBilling(date);
	}

	public static final double[] MAX_USAGE = { 0.6, 0.6, 0.6, 0.6, 0.6, 0.6,
			0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6,
			0.6, 0.6, 0.6, 0.6, 0.6 };
	public static final double[] MIN_USAGE = { 0.4, 0.4, 0.4, 0.4, 0.4, 0.4,
			0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4,
			0.4, 0.4, 0.4, 0.4, 0.4 };
	public static final int[] FORECAST_DISTANCE = { 120, 120, 120, 120, 120,
			120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120,
			120, 120, 120, 120, 120, 120, };

	@Override
	protected int getActualForecastDistance(Date date) {
		return FORECAST_DISTANCE[getIndexToDate(date)];
	}

	@Override
	protected double getActualMinUsage(Date date) {
		return MIN_USAGE[getIndexToDate(date)];
	}

	@Override
	protected double getActualMaxUsage(Date date) {
		return MAX_USAGE[getIndexToDate(date)];
	}
}
