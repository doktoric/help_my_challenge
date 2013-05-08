package com.acme.challenge.model.manager;

import java.util.Date;

import com.acme.challenge.base.QueueType;
import com.acme.challenge.model.ExportMachineStrategy;
import com.acme.challenge.model.MachineStrategy;

public class GeneralMachineManager extends MachineManager {

	private GeneralMachineManager(QueueType type) {
		this.type=type;
	}

	public static GeneralMachineManager generalMachineManager() {
		return new GeneralMachineManager(QueueType.GENERAL);
	}

	@Override
	protected void launchInitialMachines(Date dateTime) {
		launchMachine(dateTime);		
	}

}
