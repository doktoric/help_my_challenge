package com.acme.challenge.model;

import com.acme.challenge.base.QueueType;

public  class UrlMachineStrategy extends MachineStrategy {


	private UrlMachineStrategy(QueueType type) {
		
		this.type = type;
	}


	public static MachineStrategy urlMachineStrategy(QueueType type) {
		return new UrlMachineStrategy(type);
	}

}
