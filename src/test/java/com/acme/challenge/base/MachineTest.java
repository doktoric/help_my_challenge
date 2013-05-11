package com.acme.challenge.base;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MachineTest {

	private Machine underTest;

	@Before
	public void setUp(){
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 5, 11, 15, 50, 15);
		underTest = new Machine(cal.getTime());
	}
	
	@Test
	public void testSecondsTillBilling(){
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 5, 11, 17, 49, 15);
		int result = underTest.secondsTillBilling(cal.getTime());
		Assert.assertEquals(59, result);
	}
}
