package com.acme.challenge.model.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.Machine;
import com.acme.challenge.base.QueueType;

public abstract class MachineManager {

	private static final int INITIAL_MACHINE_COUNT = 3;

	protected QueueType type;

	private List<Machine> machines = new ArrayList<Machine>();

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

	protected abstract void launchInitialMachines(Date dateTime);

	public void launchMachine(Date date) {
		machines.add(new Machine(date));
		OutputWriter.writeVMCommand(date, Command.LAUNCH, type);
	}

	// TODO: implement terminate
	public void terminateIfNearBilling(Date date) {

		machines.remove(getTerminateMachine(date));
		OutputWriter.writeVMCommand(date, Command.TERMINATE, type);
	}

	private Machine getTerminateMachine(Date now) {
		Machine mostClosestMachine = machines.get(0);
		for (Machine machine : machines) {
			long diff = now.getTime() - machine.getActiveFrom().getTime();
			long mostClosestDiff = now.getTime()
					- mostClosestMachine.getActiveFrom().getTime();
			if (diff < mostClosestDiff)
				mostClosestMachine = machine;
		}

		return mostClosestMachine;
	}

	public int nrOfActiveMachines() {
		return machines.size();
	}


}
