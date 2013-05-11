package com.acme.challenge.model.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.Machine;
import com.acme.challenge.base.QueueType;

public class MachineManager {

	private MachineManager() {
	}

	private static class MachineManagerHolder {
		public static final MachineManager INSTANCE = new MachineManager();
	}

	public static MachineManager getInstance() {
		return MachineManagerHolder.INSTANCE;
	}

	private List<Machine> urlMachines = new ArrayList<Machine>();
	private List<Machine> generalMachines = new ArrayList<Machine>();
	private List<Machine> exportMachines = new ArrayList<Machine>();

	public void launchUrlMachine(Date date) {
		urlMachines.add(new Machine(date));
		OutputWriter.writeVMCommand(date, Command.LAUNCH, QueueType.URL);
	}

	public void launchGeneralMachine(Date date) {
		generalMachines.add(new Machine(date));
		OutputWriter.writeVMCommand(date, Command.LAUNCH, QueueType.GENERAL);
	}

	public void launchExportMachine(Date date) {
		exportMachines.add(new Machine(date));
		OutputWriter.writeVMCommand(date, Command.LAUNCH, QueueType.EXPORT);
	}

	private void terminateUrlMachine(Date date, Machine machine) {
		urlMachines.remove(machine);
		OutputWriter.writeVMCommand(date, Command.TERMINATE, QueueType.URL);
	}

	private void terminateGeneralMachine(Date date, Machine machine) {
		generalMachines.remove(machine);
		OutputWriter.writeVMCommand(date, Command.TERMINATE, QueueType.GENERAL);
	}

	private void terminateExportMachine(Date date, Machine machine) {
		exportMachines.remove(machine);
		OutputWriter.writeVMCommand(date, Command.TERMINATE, QueueType.EXPORT);
	}

	public void terminateUrlMachinesNearBillingTime(Date date, int seconds, int maxNrToTerminate) {
		Machine machine = closestToBillingMachine(QueueType.URL, date, seconds);
		while (machine != null && maxNrToTerminate > 0) {
			terminateUrlMachine(date, machine);
			machine = closestToBillingMachine(QueueType.URL, date, seconds);
			maxNrToTerminate--;
		} 
	}
	
	public void terminateGeneralMachinesNearBillingTime(Date date, int seconds, int maxNrToTerminate) {
		Machine machine = closestToBillingMachine(QueueType.GENERAL, date, seconds);
		while (machine != null && maxNrToTerminate > 0) {
			terminateGeneralMachine(date, machine);
			machine = closestToBillingMachine(QueueType.GENERAL, date, seconds);
			maxNrToTerminate--;
		} 
	}
	
	public void terminateExportMachinesNearBillingTime(Date date, int seconds, int maxNrToTerminate) {
		Machine machine = closestToBillingMachine(QueueType.EXPORT, date, seconds);
		while (machine != null && maxNrToTerminate > 0) {
			terminateExportMachine(date, machine);
			machine = closestToBillingMachine(QueueType.EXPORT, date, seconds);
			maxNrToTerminate--;
		} 
	}

	protected Machine closestToBillingMachine(QueueType type, Date date, int seconds) {
		List<Machine> machines = getMachineList(type);
		int minimumTillBilling = seconds;
		Machine closestMachine = null;
		for (Machine machine : machines) {
			int tillBilling = machine.secondsTillBilling(date);
			if (tillBilling < minimumTillBilling) {
				minimumTillBilling = tillBilling;
				closestMachine = machine;
			}
		}
		return closestMachine;
	}

	private List<Machine> getMachineList(QueueType type) {
		List<Machine> ret = null;
		switch (type) {
		case URL:
			ret = urlMachines;
			break;
		case GENERAL:
			ret = generalMachines;
			break;
		case EXPORT:
			ret = exportMachines;
			break;
		}
		return ret;
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

	protected List<Machine> getUrlMachines() {
		return urlMachines;
	}
	
	protected void setUrlMachines(List<Machine> urlMachines) {
		this.urlMachines = urlMachines;
	}
}
