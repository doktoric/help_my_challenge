package com.acme.challenge.model.manager;

import java.util.Date;

import com.acme.challenge.base.QueueType;

public class UrlMachineManager extends MachineManager {

	private UrlMachineManager(QueueType type) {
		this.type=type;
	}

	public static UrlMachineManager urlMachineManager() {
		return new UrlMachineManager(QueueType.URL);
	}

	@Override
	protected void launchInitialMachines(Date dateTime) {
		launchMachine(dateTime);	
		
	}
}
