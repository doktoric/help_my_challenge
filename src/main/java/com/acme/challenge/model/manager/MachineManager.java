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
	
	//TODO:
	public void terminateUrlMachine(Date date) {
//		urlMachines.remove(getTerminateMachine(date));
		OutputWriter.writeVMCommand(date, Command.TERMINATE, QueueType.URL);
	}
	
	public void terminateGeneralMachine(Date date) {
//		generalMachines.remove(getTerminateMachine(date));
		OutputWriter.writeVMCommand(date, Command.TERMINATE, QueueType.GENERAL);
	}
	
	public void terminateExportMachine(Date date) {
//		exportMachines.remove(getTerminateMachine(date));
		OutputWriter.writeVMCommand(date, Command.TERMINATE, QueueType.EXPORT);
	}

	//ez nem jo mert csak azt nezi hogy milyen regota van elinditva, nem pedig a szamlazasig az idot
	//kell bele egy modulo azt hiszem
	// private Machine getTerminateMachine(Date now) {
	// Machine mostClosestMachine = machines.get(0);
	// for (Machine machine : machines) {
	// long diff = now.getTime() - machine.getActiveFrom().getTime();
	// long mostClosestDiff = now.getTime()
	// - mostClosestMachine.getActiveFrom().getTime();
	// if (diff < mostClosestDiff)
	// mostClosestMachine = machine;
	// }
	//
	// return mostClosestMachine;
	// }

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
