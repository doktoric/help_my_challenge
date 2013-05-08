package com.acme.challenge.model;


import static com.acme.challenge.Helper.addDate;
import static com.acme.challenge.Helper.max;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.Job;
import com.acme.challenge.base.Machine;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquares;

public abstract class MachineStrategy {

	public static final double MAX_USAGE = 0.6;
	public static final double MIN_USAGE = 0.4;
	public static final int MAX_QUEUE_SIZE = 3600;
	public static final int FORECAST_DISTANCE = 120;

	protected QueueType type;
	protected List<SimulatedMachine> simulatedMachines = new ArrayList<SimulatedMachine>();
	protected List<Machine> machines = new ArrayList<Machine>();
	protected PriorityQueue<UsageStatistics> statsQueue = new PriorityQueue<>();
	protected Integer launchedVMs = 0;

	private LeastSquares leastSquares = new LeastSquares();

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

	public void addSimulatedMachine(SimulatedMachine machine) {
		simulatedMachines.add(machine);
	}

	protected Integer getBusyMachines(Date now) {
		Integer busyCount = 0;
		for (SimulatedMachine machine : simulatedMachines) {
			if (machine.isBusy(now)) {
				busyCount++;
			}
		}
		return busyCount;
	}

	public void updateStatisticQueue(Date now) {
		// System.out.println("update statitics on "+type.toString());
		UsageStatistics usageStatistics = new UsageStatistics(now,
				getBusyMachines(now));
		statsQueue.add(usageStatistics);
		if (statsQueue.size() > MAX_QUEUE_SIZE) {
			statsQueue.poll();
		}
	}

	public void processStatisticsQueueVersion0(Date now) {
		int max = 0;
		for (UsageStatistics stat : statsQueue) {
			// sum += stat.getUsedVMs();
			if (stat.getUsedVMs() > max) {
				max = stat.getUsedVMs();
			}
		}

		if (Math.ceil(launchedVMs * MAX_USAGE) <= max) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) max / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - launchedVMs; i++) {
				Date launchDate = addDate(now, 1);
				launchMachine(launchDate);
			}
		}
		if (Math.ceil(launchedVMs * MIN_USAGE) >= max && launchedVMs > 3) {
			int VMsNeeded = Math.max((int) Math.ceil((double) max / MAX_USAGE),
					3);
			for (int i = 0; i < launchedVMs - VMsNeeded; i++) {
				Date launchDate = addDate(now, 1);
				terminateMachine(launchDate, VMsNeeded);
			}
		}
	}

	public void processStatisticsQueueVersion1(Date now) {
		SimpleRegression regression = leastSquares.createRegression(statsQueue);
		double forecast = leastSquares.getForecast(regression,
				statsQueue.size() + FORECAST_DISTANCE);
		System.out.println("busy: " + getBusyMachines(now) + " predict: "
				+ forecast + " launched: " + launchedVMs);

		if (Math.ceil(launchedVMs * MAX_USAGE) <= forecast) {
			// launch as many VMs as needed to fulfill the 60% usage
			int VMsNeeded = (int) Math.ceil((double) forecast / MAX_USAGE);
			for (int i = 0; i < VMsNeeded - launchedVMs; i++) {
				launchMachine(addDate(now, 1));
			}
		}
		if (Math.ceil(launchedVMs * MIN_USAGE) >= forecast && launchedVMs > 5) {
			int VMsNeeded = Math.max(
					(int) Math.ceil((double) forecast / MAX_USAGE), 5);
			// for (int i = 0; i < launchedVMs - VMsNeeded; i++) {
			terminateMachine(addDate(now, 1), VMsNeeded);
			// }
		}
	}

	

	public void launchMachine(Date date) {
		increaseVmSize();
		OutputWriter.writeVMCommand(date, Command.LAUNCH, type);
	}

	public void terminateMachine(Date date, int VMsNeeded) {
		// TODO Auto-generated method stub
		for (SimulatedMachine machine : simulatedMachines) {
			long diff = date.getTime() - machine.getActiveFrom().getTime();
			System.out.println("now:" + date);
			System.out.println("active from:"
					+ machine.getActiveFrom());
			if (VMsNeeded < launchedVMs && isInTerminateTime(diff)) {
				decreaseVmSize();
				OutputWriter.writeVMCommand(date, Command.TERMINATE, type);
			}
		}

	}

	protected abstract boolean isInTerminateTime(long diff);

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

	protected boolean hasFoundAvailableMachine(Job job) {
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

	protected List<SimulatedMachine> randomMachines() {
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
