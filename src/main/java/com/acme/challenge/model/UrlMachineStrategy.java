package com.acme.challenge.model;

import java.util.Date;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.QueueType;

public class UrlMachineStrategy extends MachineStrategy {

	private Integer END_OF_HOUR = 6000;

	public UrlMachineStrategy(QueueType type) {

		this.type = type;
	}

	public static MachineStrategy urlMachineStrategy(QueueType type) {
		return new UrlMachineStrategy(type);
	}

	@Override
	protected boolean isInTerminateTime(long diff) {
		boolean isTerminated = false;
		isTerminated=(diff<END_OF_HOUR);
		return isTerminated;
	}

}
