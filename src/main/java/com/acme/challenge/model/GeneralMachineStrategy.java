package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.base.QueueType;

public class GeneralMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 6000;

	private GeneralMachineStrategy(MachineManager machineManager, QueueType type) {
		super(machineManager, type);
	}

	public static MachineStrategy generalMachineStrategy(MachineManager machineManager, QueueType type) {
		return new GeneralMachineStrategy(machineManager, type);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated = false;
		isTerminated=(diff<END_OF_HOUR);
		return isTerminated;
	}

	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveGeneralMachines();
	}
	
	@Override
	protected void launchVM(Date date) {
		machineManager.launchMachine(date, QueueType.GENERAL);
	}
	
	@Override
	protected void nominateToTermination(Date date) {
		machineManager.terminateIfNearBilling(date, QueueType.GENERAL);
	}

}
