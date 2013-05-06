package com.acme.challenge.model;

import com.acme.challenge.base.QueueType;
import static com.acme.challenge.SimulatedMachineManager.MAX_QUEUE_SIZE;
import static com.acme.challenge.SimulatedMachineManager.MAX_USAGE;
import static com.acme.challenge.SimulatedMachineManager.MIN_USAGE;


public  class ExportMachineStrategy extends MachineStrategy {


	private ExportMachineStrategy(QueueType type) {
		this.type = type;
	}


	public static MachineStrategy exportMachineStrategy(QueueType type) {
		return new ExportMachineStrategy(type);
	}

}
