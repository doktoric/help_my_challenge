package com.acme.challenge.model;

import static com.acme.challenge.SimulatedMachineManager.MAX_QUEUE_SIZE;
import static com.acme.challenge.SimulatedMachineManager.MAX_USAGE;
import static com.acme.challenge.SimulatedMachineManager.MIN_USAGE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.acme.challenge.Command;
import com.acme.challenge.Job;
import com.acme.challenge.OutputWriter;
import com.acme.challenge.QueueType;
import com.acme.challenge.SimulatedMachine;
import com.acme.challenge.UsageStatistics;

public class MachineHolder {

	private QueueType type;
	private List<SimulatedMachine> machines = new ArrayList<SimulatedMachine>();
	private PriorityQueue<UsageStatistics> statsQueue = new PriorityQueue<>();
	private Integer launchedVMs = 0;

	public MachineHolder(QueueType type) {
		super();
		this.type = type;
	}

	public void decreaseVmSize() {
		this.launchedVMs--;
	}

	public void increaseVmSize() {
		this.launchedVMs++;
	}

	public void addSimulatedMachine(SimulatedMachine machine) {
		machines.add(machine);
	}

	private Integer getBusyMachines(Date now) {
		Integer busyCount = 0;
		for (SimulatedMachine machine : machines) {
			if (machine.isBusy(now)) {
				busyCount++;
			}
		}
		return busyCount;
	}

	public void updateStatisticQue(Date now) {
		Integer busyCount=getBusyMachines(now);
		UsageStatistics usageStatistics=new UsageStatistics(now, busyCount);
		statsQueue.add(usageStatistics);
		if (statsQueue.size() > MAX_QUEUE_SIZE) {
			statsQueue.poll();
		}
	}

	public void processStatisticsQueue(Date now) {
		//int sum = 0;
		int max = 0;
		for (UsageStatistics stat : statsQueue) {
			//sum += stat.getUsedVMs();
			if (stat.getUsedVMs() > max) {
				max = stat.getUsedVMs();
			}
		}

		System.out.println("queue type " + type + " launched: " + launchedVMs + " max: " + max);
		if (Math.ceil(launchedVMs * MAX_USAGE) <= max) {
			
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) max / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - launchedVMs; i++) {
				Date launchDate=addDate(now, 1);
				launchMachine(launchDate);
			}
		}
		if (Math.ceil(launchedVMs * MIN_USAGE) >= max && launchedVMs > 3) {
			int VMsNeeded = Math.max((int) Math.ceil((double) max / MAX_USAGE), 3);
			for (int i = 0; i < launchedVMs - VMsNeeded; i++) {
				Date launchDate=addDate(now, 1);
				terminateMachine(launchDate);
			}
		}
	}

	private Date addDate(Date date, double seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, (int) (seconds * 1000));
		return calendar.getTime();
	}

	public void launchMachine(Date date) {
		increaseVmSize();
		OutputWriter.writeVMCommand(date, Command.LAUNCH, type);
	}

	public void terminateMachine(Date date) {
		decreaseVmSize();
		OutputWriter.writeVMCommand(date, Command.TERMINATE, type);
	}

	public void simulateVMLoad(Job job) {
		boolean foundAvailableMachine = hasFoundedAvailableMachine(job);
		if (!foundAvailableMachine) {
			Date jobDate=job.getDateTime();
			Date busyTill=addDate(jobDate, job.getRuntimeInSeconds());
			SimulatedMachine simulatedMachine=new SimulatedMachine(jobDate, busyTill);
			addSimulatedMachine(simulatedMachine);
		}
	}

	private boolean hasFoundedAvailableMachine(Job job) {
		boolean foundAvailableMachine = false;
		List<SimulatedMachine> simulatedMachines=randomMachines();
		for (SimulatedMachine machine : simulatedMachines) {
			Date busyDate=addDate(job.getDateTime(), 5d);
			if (!machine.isBusy(busyDate)) {
				Date busyTillDate=machine.getBusyTill();
				Date maxDate=max(busyTillDate, job.getDateTime());
				Date date = addDate(maxDate,job.getRuntimeInSeconds());
				machine.setBusyTill(date);
				foundAvailableMachine = true;
				break;
			}
		}
		return foundAvailableMachine;
	}

	private List<SimulatedMachine> randomMachines() {
		List<SimulatedMachine> ret = new ArrayList<SimulatedMachine>();
		if (machines.size() > 0) {
			int size = machines.size();
			Random rnd = new Random();
			int randomStart = rnd.nextInt(size);
			for (int i = 0; i < size; i++) {
				ret.add(machines.get((randomStart + i) % size));
			}
		}
		return ret;
	}

	private Date max(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return date1;
		} else {
			return date2;
		}
	}

	public static MachineHolder machineCollector(QueueType type) {
		return new MachineHolder(type);
	}

}
