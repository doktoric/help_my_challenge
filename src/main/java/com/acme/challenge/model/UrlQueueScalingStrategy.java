package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.TmpFileWriter;
import com.acme.challenge.model.manager.MachineManager;

public class UrlQueueScalingStrategy extends ScalingStrategy {

	private static final int MINIMUM_MACHINE_COUNT = 5;
	// average url job time * 2
	private Integer EARLIEST_TERMINATION = 2;

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
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveUrlMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchUrlMachine(date);
	}

	@Override
	protected void nominateToTermination(Date date, int maxNrToTerminate) {
		machineManager.terminateUrlMachinesNearBillingTime(date, EARLIEST_TERMINATION, maxNrToTerminate);
	}

	public static final double[] MAX_USAGE = { 4.0, 7.0, 5.0, 3.5, 4.0, 6.0, 8.0, 7.0, 7.0, 13.0, 5.0, 6.33, 5.50, 5.50, 4.0, 4.0, 4.67, 3.25, 4.33, 3.25, 4.25, 4.67,
		5.2, 4.0 };
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

	@Override
	public void logForecast(Date date, double forecast) {
//		TmpFileWriter.getInstance().writeline(String.valueOf(forecast));
		
	}
	
	@Override
	protected double getChanceOfTermination() {
		return 0.7;
	}
}
