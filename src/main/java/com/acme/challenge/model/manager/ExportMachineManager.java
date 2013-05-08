package com.acme.challenge.model.manager;

import java.util.Date;

import com.acme.challenge.base.QueueType;

public class ExportMachineManager extends MachineManager{

	
	
	private ExportMachineManager(QueueType type) {
		this.type=type;
	}

	public static ExportMachineManager exportMachineManager() {
		return new ExportMachineManager(QueueType.EXPORT);
	}

	@Override
	protected void launchInitialMachines(Date dateTime) {
		launchMachine(dateTime);	
		
	}
}
