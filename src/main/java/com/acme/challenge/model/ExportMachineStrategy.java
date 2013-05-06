package com.acme.challenge.model;

import com.acme.challenge.base.QueueType;


public  class ExportMachineStrategy extends MachineStrategy {


	private ExportMachineStrategy(QueueType type) {
		this.type = type;
	}


	public static MachineStrategy exportMachineStrategy(QueueType type) {
		return new ExportMachineStrategy(type);
	}

}
