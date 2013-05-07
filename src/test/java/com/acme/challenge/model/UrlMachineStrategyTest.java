package com.acme.challenge.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.acme.challenge.base.QueueType;

public class UrlMachineStrategyTest {

	
	MachineStrategy underTest;
	
	@Before
	public void setUp() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		underTest=new UrlMachineStrategy(QueueType.URL);
		List<SimulatedMachine> machines=new ArrayList<>();
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
		
		
	}
	
	@Test
	public void test() {
		
	}

}
