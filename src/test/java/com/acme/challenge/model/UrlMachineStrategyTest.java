package com.acme.challenge.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.acme.challenge.base.QueueType;
import com.acme.challenge.simulation.SimulatedMachine;

public class UrlMachineStrategyTest {

	MachineStrategy underTest;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Before
	public void setUp() throws ParseException {
		underTest = new UrlMachineStrategy(null, QueueType.URL);

	}

	@Test
	public void testgetForecast1() throws ParseException {
		Assert.assertEquals(120, underTest.getActualForecastDistance(dateFormat
				.parse("2013-03-01 00:23:59")));
	}

	@Test
	public void testgetForecast2() throws ParseException {
		Assert.assertEquals(120, underTest.getActualForecastDistance(dateFormat
				.parse("2013-03-01 00:59:59")));
	}

	@Test
	public void testgetMax1() throws ParseException {
		Assert.assertEquals(0.6, underTest.getActualMaxUsage(dateFormat
				.parse("2013-03-01 00:23:59")));
	}

	@Test
	public void testgetMax2() throws ParseException {
		Assert.assertEquals(0.6, underTest.getActualMaxUsage(dateFormat
				.parse("2013-03-01 00:59:59")));
	}

	@Test
	public void testgetMin1() throws ParseException {
		Assert.assertEquals(0.4, underTest.getActualMinUsage(dateFormat
				.parse("2013-03-01 00:23:59")));
	}

	@Test
	public void testgetMin2() throws ParseException {
		Assert.assertEquals(0.4, underTest.getActualMinUsage(dateFormat
				.parse("2013-03-01 00:59:59")));
	}

}
