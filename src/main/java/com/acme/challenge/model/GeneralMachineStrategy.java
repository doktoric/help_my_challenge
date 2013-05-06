package com.acme.challenge.model;

import com.acme.challenge.base.QueueType;


public  class GeneralMachineStrategy extends MachineStrategy{

	private GeneralMachineStrategy(QueueType type) {
		this.type = type;
	}


	public static MachineStrategy generalMachineStrategy(QueueType type) {
		return new GeneralMachineStrategy(type);
	}

}
