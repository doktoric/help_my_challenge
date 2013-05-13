package com.acme.challenge.simulation;

import static com.acme.challenge.Helper.addDate;
import static com.acme.challenge.Helper.max;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.base.UsageStatistics;

public class VirtualLoadSimulator {

	public static final int MAX_QUEUE_SIZE = 300;

	private VirtualLoadSimulator() {
	}

	private static class SimulatedMachineManagerHolder {
		public static final VirtualLoadSimulator INSTANCE = new VirtualLoadSimulator();
	}

	public static VirtualLoadSimulator getInstance() {
		return SimulatedMachineManagerHolder.INSTANCE;
	}

	private List<SimulatedMachine> urlQMachines = new ArrayList<SimulatedMachine>();
	private List<SimulatedMachine> generalQMachines = new ArrayList<SimulatedMachine>();
	private List<SimulatedMachine> exportQMachines = new ArrayList<SimulatedMachine>();

	private PriorityQueue<UsageStatistics> urlStatsQueue = new PriorityQueue<>();
	private PriorityQueue<UsageStatistics> generalStatsQueue = new PriorityQueue<>();
	private PriorityQueue<UsageStatistics> exportStatsQueue = new PriorityQueue<>();

	public void updateStatisticsQueue(Date now, QueueType queueType) {
		PriorityQueue<UsageStatistics> statsQueue = null;
		List<SimulatedMachine> simulatedMachines = null;
		switch (queueType) {
		case URL:
			statsQueue = urlStatsQueue;
			simulatedMachines = urlQMachines;
			break;
		case GENERAL:
			statsQueue = generalStatsQueue;
			simulatedMachines = generalQMachines;
			break;
		case EXPORT:
			statsQueue = exportStatsQueue;
			simulatedMachines = exportQMachines;
			break;
		default:
			break;
		}
		statsQueue.add(new UsageStatistics(now, getBusyMachines(now, simulatedMachines)));
		if (statsQueue.size() > MAX_QUEUE_SIZE) {
			statsQueue.poll();
		}
	}

	public Integer getBusyMachines(Date now, List<SimulatedMachine> machines) {
		Integer ret = 0;
		for (SimulatedMachine machine : machines) {
			if (machine.isBusy(now)) {
				ret++;
			}
		}
		return ret;
	}

	public void addMachine(SimulatedMachine machine, QueueType type) {
		switch (type) {
		case URL:
			urlQMachines.add(machine);
			break;
		case EXPORT:
			exportQMachines.add(machine);
			break;
		case GENERAL:
			generalQMachines.add(machine);
			break;
		}
	}

	public void simulateVMLoad(Job job) {
		List<SimulatedMachine> simulatedMachines = null;
		switch (job.getQueueType()) {
		case URL:
			simulatedMachines = urlQMachines;
			break;
		case EXPORT:
			simulatedMachines = exportQMachines;
			break;
		case GENERAL:
			simulatedMachines = generalQMachines;
			break;
		}

		boolean foundAvailableMachine = false;
		for (SimulatedMachine machine : randomMachines(simulatedMachines)) {
			if (!machine.isBusy(addDate(job.getDateTime(), 5d))) {
				machine.setBusyTill(addDate(max(machine.getBusyTill(), job.getDateTime()), job.getRuntimeInSeconds()));
				foundAvailableMachine = true;
				break;
			}
		}
		if (!foundAvailableMachine) {
			addMachine(new SimulatedMachine(job.getDateTime(), addDate(job.getDateTime(), job.getRuntimeInSeconds())), job.getQueueType());
		}
	}

	private List<SimulatedMachine> randomMachines(List<SimulatedMachine> machines) {
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

	public PriorityQueue<UsageStatistics> getUrlStatsQueue() {
		return urlStatsQueue;
	}

	public PriorityQueue<UsageStatistics> getExportStatsQueue() {
		return exportStatsQueue;
	}

	public PriorityQueue<UsageStatistics> getGeneralStatsQueue() {
		return generalStatsQueue;
	}

	public void updateStatistics(Date now) {
		updateStatisticsQueue(now, QueueType.URL);
		updateStatisticsQueue(now, QueueType.GENERAL);
		updateStatisticsQueue(now, QueueType.EXPORT);
	}

}
