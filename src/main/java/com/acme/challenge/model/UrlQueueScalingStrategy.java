package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.model.manager.MachineManager;

public class UrlQueueScalingStrategy extends ScalingStrategy {

	private static final int MINIMUM_MACHINE_COUNT = 5;
	private Integer END_OF_HOUR = 10000;

	private UrlQueueScalingStrategy() {
		super(MachineManager.getInstance());
	}

	private static class UrlMachineStrategyHolder {
		public static final UrlQueueScalingStrategy INSTANCE = new UrlQueueScalingStrategy();
	}

	public static UrlQueueScalingStrategy getInstance() {
		return UrlMachineStrategyHolder.INSTANCE;
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated = false;
		isTerminated = (diff < END_OF_HOUR);
		return isTerminated;
	}

	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveUrlMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchUrlMachine(date);
	}

	@Override
	protected void nominateToTermination(Date date) {
		machineManager.terminateUrlMachine(date);
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

	@Override
	public void launchInitialMachines(Date date) {
		for (int i = 0; i < MINIMUM_MACHINE_COUNT; i++) {
			machineManager.launchUrlMachine(date);
		}		
	}
	
	@Override
	protected int minimumMachineCount() {
		return MINIMUM_MACHINE_COUNT;
	}
}
