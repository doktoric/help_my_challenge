package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.QueueType;

public class GeneralMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 6000;

	private GeneralMachineStrategy(QueueType type) {
		this.type = type;
		manager=new SimulatedMachineManager(QueueType.GENERAL);
	}

	public static MachineStrategy generalMachineStrategy(QueueType type) {
		return new GeneralMachineStrategy(type);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated = false;
		isTerminated=(diff<END_OF_HOUR);
		return isTerminated;
	}

}
