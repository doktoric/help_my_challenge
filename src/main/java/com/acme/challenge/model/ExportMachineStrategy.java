package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.QueueType;

public class ExportMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 6000;

	private ExportMachineStrategy(QueueType type) {
		this.type = type;
		manager=new SimulatedMachineManager(QueueType.EXPORT);
	}

	public static MachineStrategy exportMachineStrategy(QueueType type) {
		return new ExportMachineStrategy(type);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated=false;
		isTerminated=(diff<END_OF_HOUR);
		return isTerminated;
	}

	

}
