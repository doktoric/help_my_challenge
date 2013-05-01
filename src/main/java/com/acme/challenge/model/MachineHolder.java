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
	private List<SimulatedMachine> QMachines = new ArrayList<SimulatedMachine>();
	private PriorityQueue<UsageStatistics> StatsQueue = new PriorityQueue<>();
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
		QMachines.add(machine);
	}

	private Integer getBusyMachines(Date now) {
		Integer ret = 0;
		for (SimulatedMachine machine : QMachines) {
			if (machine.isBusy(now)) {
				ret++;
			}
		}
		return ret;
	}

	public void updateStatisticQue(Date now) {
		StatsQueue.add(new UsageStatistics(now, getBusyMachines(now)));
		if (StatsQueue.size() > MAX_QUEUE_SIZE) {
			StatsQueue.poll();
		}
	}

	public void processStatisticsQueue(Date now) {
		int sum = 0;
		int max = 0;
		for (UsageStatistics stat : StatsQueue) {
			sum += stat.getUsedVMs();
			if (stat.getUsedVMs() > max) {
				max = stat.getUsedVMs();
			}
		}

		System.out.println("queue type " + type + " launched: " + launchedVMs
				+ " max: " + max);
		if (Math.ceil(launchedVMs * MAX_USAGE) <= max) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) max / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - launchedVMs; i++) {
				launchMachine(addDate(now, 1));
			}
		}
		if (Math.ceil(launchedVMs * MIN_USAGE) >= max && launchedVMs > 3) {
			int VMsNeeded = Math.max((int) Math.ceil((double) max / MAX_USAGE),
					3);
			for (int i = 0; i < launchedVMs - VMsNeeded; i++) {
				terminateMachine(addDate(now, 1));
			}
		}
	}

	private Date addDate(Date date, double seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MILLISECOND, (int) (seconds * 1000));
		return cal.getTime();
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
		boolean foundAvailableMachine = false;
		for (SimulatedMachine machine : randomMachines()) {
			if (!machine.isBusy(addDate(job.getDateTime(), 5d))) {
				machine.setBusyTill(addDate(
						max(machine.getBusyTill(), job.getDateTime()),
						job.getRuntimeInSeconds()));
				foundAvailableMachine = true;
				break;
			}
		}
		if (!foundAvailableMachine) {
			addSimulatedMachine(
					new SimulatedMachine(job.getDateTime(), addDate(job.getDateTime(), job.getRuntimeInSeconds())));
		}
	}

	private List<SimulatedMachine> randomMachines() {
		List<SimulatedMachine> ret = new ArrayList<SimulatedMachine>();
		if (QMachines.size() > 0) {
			int size = QMachines.size();
			Random rnd = new Random();
			int randomStart = rnd.nextInt(size);
			for (int i = 0; i < size; i++) {
				ret.add(QMachines.get((randomStart + i) % size));
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
