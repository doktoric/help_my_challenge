package com.acme.challenge.model.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acme.challenge.base.Machine;
import com.acme.challenge.base.QueueType;

public class MachineManagerTest {

	MachineManager underTest;

	@Before
	public void setUp() {
		underTest = MachineManager.getInstance();

		Calendar cal = Calendar.getInstance();

		List<Machine> machines = new ArrayList<>();
		cal.set(2013, 5, 11, 15, 40, 15);
		machines.add(new Machine(cal.getTime()));
		cal.set(2013, 5, 11, 15, 50, 15);
		machines.add(new Machine(cal.getTime()));
		cal.set(2013, 5, 11, 16, 20, 15);
		machines.add(new Machine(cal.getTime()));
		cal.set(2013, 5, 11, 16, 30, 15);
		machines.add(new Machine(cal.getTime()));
		underTest.setUrlMachines(machines);
	}

	@Test
	public void testClosestToBillingMachineWhenThereIsOnePossibleTerminatableMachine() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 5, 11, 16, 45, 15);
		Machine machine = underTest.closestToBillingMachine(QueueType.URL, cal.getTime(), 600);

		cal.set(2013, 5, 11, 15, 50, 15);
		Assert.assertEquals(cal.getTime().getHours(), machine.getActiveFrom().getHours());
		Assert.assertEquals(cal.getTime().getMinutes(), machine.getActiveFrom().getMinutes());
		Assert.assertEquals(cal.getTime().getSeconds(), machine.getActiveFrom().getSeconds());
	}
	
	@Test
	public void testClosestToBillingMachineWhenThereIsMoreThanOnePossibleTerminatableMachines() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 5, 11, 16, 39, 15);
		Machine machine = underTest.closestToBillingMachine(QueueType.URL, cal.getTime(), 1200);

		cal.set(2013, 5, 11, 15, 40, 15);
		Assert.assertEquals(cal.getTime().getHours(), machine.getActiveFrom().getHours());
		Assert.assertEquals(cal.getTime().getMinutes(), machine.getActiveFrom().getMinutes());
		Assert.assertEquals(cal.getTime().getSeconds(), machine.getActiveFrom().getSeconds());
	}

	@Test
	public void testClosestToBillingMachineWhenThereIsNoPossibleTerminatableMachine() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 5, 11, 16, 45, 15);
		Machine machine = underTest.closestToBillingMachine(QueueType.URL, cal.getTime(), 60);
		Assert.assertEquals(null, machine);

	}
	
	@Test
	public void testTerminateURLMachinesNearBillingTime(){
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 5, 11, 16, 39, 15);
		underTest.terminateUrlMachinesNearBillingTime(cal.getTime(), 1200, 3);
		Assert.assertEquals(2, underTest.getUrlMachines().size());
	}

}
