package com.acme.challenge.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.acme.challenge.base.QueueType;

public class UrlMachineStrategyTest {

	
	MachineStrategy underTest;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Before
	public void setUp() throws ParseException{
	
		underTest=new UrlMachineStrategy(QueueType.URL);
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:54:52"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:52"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:58:52"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:45"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:52"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:51"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:47"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:53"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:55"), dateFormat.parse("2013-03-01 00:00:52")));
		underTest.addSimulatedMachine(new SimulatedMachine(dateFormat.parse("2013-03-01 00:59:32"), dateFormat.parse("2013-03-01 00:00:52")));
		
		underTest.launchedVMs=10; 
	}
	
	@Test
	public void testWithTerminatingWith7VM() throws ParseException {
		underTest.terminateMachine(dateFormat.parse("2013-03-01 00:59:59"), 7);
		Assert.assertEquals(new Integer(7), underTest.launchedVMs);
	}
	
	@Test
	public void testWithTerminatingWith9VM() throws ParseException {
		underTest.terminateMachine(dateFormat.parse("2013-03-01 00:59:59"), 9);
		Assert.assertEquals(new Integer(9), underTest.launchedVMs);
	}
	
	@Test
	public void testWithTerminatingWith9VMButFalseTime() throws ParseException {
		underTest.terminateMachine(dateFormat.parse("2013-03-02 00:59:59"), 9);
		Assert.assertEquals(new Integer(10), underTest.launchedVMs);
	}

}
