package com.acme.challenge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.Machine;
import com.acme.challenge.base.QueueType;

public class MachineManager {

	private static final int INITIAL_MACHINE_COUNT = 3;

	private List<Machine> urlMachines = new ArrayList<Machine>();
	private List<Machine> generalMachines = new ArrayList<Machine>();
	private List<Machine> exportMachines = new ArrayList<Machine>();

	// public void terminateMachine(Date date, int VMsNeeded, QueueType type) {
	// // TODO Auto-generated method stub
	// for (SimulatedMachine machine : manager.simulatedMachines) {
	// long diff = date.getTime() - machine.getActiveFrom().getTime();
	// System.out.println("now:" + date);
	// System.out.println("active from:" + machine.getActiveFrom());
	// if (VMsNeeded < manager.launchedVMs && isInTerminateTime(diff)) {
	// manager.decreaseVmSize();
	// OutputWriter.writeVMCommand(date, Command.TERMINATE, type);
	// }
	// }
	// }

	public void launchInitialMachines(Date dateTime) {
		for (int i = 0; i < INITIAL_MACHINE_COUNT; i++) {
			launchMachine(dateTime, QueueType.URL);
			launchMachine(dateTime, QueueType.EXPORT);
			launchMachine(dateTime, QueueType.GENERAL);
		}
	}

	public void launchMachine(Date date, QueueType type) {
		switch (type) {
		case URL:
			urlMachines.add(new Machine(date));
			break;
		case GENERAL:
			generalMachines.add(new Machine(date));
			break;
		case EXPORT:
			exportMachines.add(new Machine(date));
			break;
		}
		OutputWriter.writeVMCommand(date, Command.LAUNCH, type);
	}

	//TODO: implement terminate
	public void terminateIfNearBilling(Date date, QueueType type) {
		switch (type) {
		case URL:
			
			break;
		case GENERAL:

			break;
		case EXPORT:

			break;
		}
		OutputWriter.writeVMCommand(date, Command.TERMINATE, type);
	}

	public int nrOfActiveUrlMachines() {
		return urlMachines.size();
	}

	public int nrOfActiveExportMachines() {
		return exportMachines.size();
	}

	public int nrOfActiveGeneralMachines() {
		return generalMachines.size();
	}

}
