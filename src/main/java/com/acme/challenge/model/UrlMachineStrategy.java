package com.acme.challenge.model;

import static com.acme.challenge.SimulatedMachineManager.MAX_QUEUE_SIZE;
import static com.acme.challenge.SimulatedMachineManager.MAX_USAGE;
import static com.acme.challenge.SimulatedMachineManager.MIN_USAGE;


import com.acme.challenge.base.QueueType;

public  class UrlMachineStrategy extends MachineStrategy {


	private UrlMachineStrategy(QueueType type) {
		
		this.type = type;
	}


	public static MachineStrategy urlMachineStrategy(QueueType type) {
		return new UrlMachineStrategy(type);
	}

}
