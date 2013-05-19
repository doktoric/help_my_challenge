package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.TmpFileWriter;
import com.acme.challenge.model.manager.MachineManager;

public class GeneralQueueScalingStrategy extends ScalingStrategy {

	private static final int MINIMUM_MACHINE_COUNT = 5;
	// average general job time
	private Integer EARLIEST_TERMINATION = 30;

	private GeneralQueueScalingStrategy() {
		super(MachineManager.getInstance());
	}

	private static class GeneralMachineStrategyHolder {
		public static final GeneralQueueScalingStrategy INSTANCE = new GeneralQueueScalingStrategy();
	}

	public static GeneralQueueScalingStrategy getInstance() {
		return GeneralMachineStrategyHolder.INSTANCE;
	}


	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveGeneralMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchGeneralMachine(date);
	}

	@Override
	protected void nominateToTermination(Date date, int maxNrToTerminate) {
		machineManager.terminateGeneralMachinesNearBillingTime(date, EARLIEST_TERMINATION, maxNrToTerminate);
	}

	public static final double[] MAX_USAGE = { 3.22, 2.86, 2.86, 2.57, 3.0, 3.57, 3.18, 3.0, 4.0, 3.0, 3.0, 3.4, 3.0, 3.14, 2.75, 2.78, 2.4, 2.18, 2.48, 2.67, 2.56, 2.2,
			2.5, 2.73 };
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
			machineManager.launchGeneralMachine(date);
		}
	}

	@Override
	protected int minimumMachineCount() {
		return MINIMUM_MACHINE_COUNT;
	}

	@Override
	public void logForecast(Date date, double forecast) {
		TmpFileWriter.getInstance().writeline(date + "  " + String.valueOf(forecast));
		
	}
	
	@Override
	protected double getChanceOfTermination() {
		return 0.1;
	}
}
