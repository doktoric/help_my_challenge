package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.base.QueueType;

public class ExportMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 6000;

	private ExportMachineStrategy(MachineManager machineManager, QueueType type) {
		super(machineManager, type);
	}

	public static MachineStrategy exportMachineStrategy(MachineManager machineManager, QueueType type) {
		return new ExportMachineStrategy(machineManager, type);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated=false;
		isTerminated=(diff<END_OF_HOUR);
		return isTerminated;
	}

	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveExportMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchMachine(date, QueueType.EXPORT);
	}

	@Override
	protected void nominateToTermination(Date date) {
		machineManager.terminateIfNearBilling(date, QueueType.EXPORT);
	}
}
