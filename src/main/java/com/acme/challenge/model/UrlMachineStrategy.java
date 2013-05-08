package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.base.QueueType;

public class UrlMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 10000;

	public UrlMachineStrategy(MachineManager machineManager, QueueType type) {
		super(machineManager, type);
	}

	public static MachineStrategy urlMachineStrategy(MachineManager machineManager, QueueType type) {
		return new UrlMachineStrategy(machineManager, type);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated = false;
		isTerminated=(diff<END_OF_HOUR);
		return isTerminated;
	}

	@Override
	protected int getNrOfLaunchedVMs() {
		return machineManager.nrOfActiveUrlMachines();
	}

	@Override
	protected void launchVM(Date date) {
		machineManager.launchMachine(date, QueueType.URL);
	}
	
	@Override
	protected void nominateToTermination(Date date) {
		machineManager.terminateIfNearBilling(date, QueueType.URL);
	}
}
