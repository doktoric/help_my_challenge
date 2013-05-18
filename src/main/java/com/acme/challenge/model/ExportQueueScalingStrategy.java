package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.model.manager.MachineManager;

public class ExportQueueScalingStrategy extends ScalingStrategy {

	private static final int MINIMUM_MACHINE_COUNT = 5;
	//average export job time
	private Integer EARLIEST_TERMINATION = 18;

	private ExportQueueScalingStrategy() {
		super(MachineManager.getInstance());
	}

	private static class ExportMachineStrategyHolder {
		public static final ExportQueueScalingStrategy INSTANCE = new ExportQueueScalingStrategy();
	}

	public static ExportQueueScalingStrategy getInstance() {
		return ExportMachineStrategyHolder.INSTANCE;
	}

	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveExportMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchExportMachine(date);
	}

	@Override
	protected void nominateToTermination(Date date, int maxNrToTerminate) {
		machineManager.terminateExportMachinesNearBillingTime(date, EARLIEST_TERMINATION, maxNrToTerminate);
	}

	public static final double[] MAX_USAGE = { 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6,
			0.6, 0.6 };
	public static final double[] MIN_USAGE = { 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4,
			0.4, 0.4 };
	public static final int[] FORECAST_DISTANCE = { 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120,
			120, 120, 120, };

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
			machineManager.launchExportMachine(date);
		}
	}

	@Override
	protected int minimumMachineCount() {
		return MINIMUM_MACHINE_COUNT;
	}

	@Override
	public void logForecast(Date date, double forecast) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected double getChanceOfTermination() {
		return 0.15;
	}
}
