package com.acme.challenge.model;

import static com.acme.challenge.Helper.addDate;
import static com.acme.challenge.Helper.max;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;

public class SimulatedMachineManager {

	
	protected Integer launchedVMs = 0;
	protected QueueType type;
	protected List<SimulatedMachine> simulatedMachines = new ArrayList<SimulatedMachine>();
	
	
	public SimulatedMachineManager(QueueType type) {
		this.type = type;
	}



	public void decreaseVmSize() {
		// System.out.println("Decreased VM count on "+
		// type.toString()+" queue where the previous was: " + launchedVMs+
		// " and the increased: "+(launchedVMs+1) );
		this.launchedVMs--;
	}

	
	
	public void increaseVmSize() {
		// System.out.println("Increased VM count on "+
		// type.toString()+" queue where the previous was: " + launchedVMs+
		// " and the increased: "+(launchedVMs-1) );
		this.launchedVMs++;
	}
	
	
	public Integer getBusyMachines(Date now) {
		Integer busyCount = 0;
		for (SimulatedMachine machine : simulatedMachines) {
			if (machine.isBusy(now)) {
				busyCount++;
			}
		}
		return busyCount;
	}
	
	public void addSimulatedMachine(SimulatedMachine machine) {
		simulatedMachines.add(machine);
	}
	
	public void simulateVMLoad(Job job) {
		boolean foundAvailableMachine = hasFoundAvailableMachine(job);
		if (!foundAvailableMachine) {
			System.out.println("No available machine on " + type.toString());
			Date jobDate = job.getDateTime();
			Date busyTill = addDate(jobDate, job.getRuntimeInSeconds());
			SimulatedMachine simulatedMachine = new SimulatedMachine(jobDate,
					busyTill);
			addSimulatedMachine(simulatedMachine);
		}
	}

	public boolean hasFoundAvailableMachine(Job job) {
		boolean foundAvailableMachine = false;
		List<SimulatedMachine> simulatedMachines = randomMachines();
		for (SimulatedMachine machine : simulatedMachines) {
			Date busyDate = addDate(job.getDateTime(), 5d);
			if (!machine.isBusy(busyDate)) {
				Date busyTillDate = machine.getBusyTill();
				Date maxDate = max(busyTillDate, job.getDateTime());
				Date date = addDate(maxDate, job.getRuntimeInSeconds());
				machine.setBusyTill(date);
				foundAvailableMachine = true;
				break;
			}
		}
		return foundAvailableMachine;
	}
	
	
	public List<SimulatedMachine> randomMachines() {
		List<SimulatedMachine> ret = new ArrayList<SimulatedMachine>();
		if (simulatedMachines.size() > 0) {
			int size = simulatedMachines.size();
			Random rnd = new Random();
			int randomStart = rnd.nextInt(size);
			for (int i = 0; i < size; i++) {
				ret.add(simulatedMachines.get((randomStart + i) % size));
			}
		}
		return ret;
	}
	
}
