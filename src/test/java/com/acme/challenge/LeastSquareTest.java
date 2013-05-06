package com.acme.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquare;

public class LeastSquareTest {

	private LeastSquare underTest;
	private PriorityQueue<UsageStatistics> statsQueue;
	
	@Before
	public void setUp(){
		underTest=new LeastSquare();
		statsQueue=new PriorityQueue<UsageStatistics>();
		Calendar cal = Calendar.getInstance();
		cal.set(2009, Calendar.DECEMBER, 1);
		statsQueue.add(new UsageStatistics(cal.getTime(),0));
		cal.set(2009, Calendar.DECEMBER, 2);
		statsQueue.add(new UsageStatistics(cal.getTime(),1));
		cal.set(2009, Calendar.DECEMBER, 3);
		statsQueue.add(new UsageStatistics(cal.getTime(),2));
		cal.set(2009, Calendar.DECEMBER, 4);
		statsQueue.add(new UsageStatistics(cal.getTime(),3));
		cal.set(2009, Calendar.DECEMBER, 5);
		statsQueue.add(new UsageStatistics(cal.getTime(),4));
		cal.set(2009, Calendar.DECEMBER, 6);
		statsQueue.add(new UsageStatistics(cal.getTime(),5));
		cal.set(2009, Calendar.DECEMBER, 7);
		statsQueue.add(new UsageStatistics(cal.getTime(),6));
		cal.set(2009, Calendar.DECEMBER, 8);
		statsQueue.add(new UsageStatistics(cal.getTime(),7));
		cal.set(2009, Calendar.DECEMBER, 9);
		statsQueue.add(new UsageStatistics(cal.getTime(),8));
		cal.set(2009, Calendar.DECEMBER, 10);
		statsQueue.add(new UsageStatistics(cal.getTime(),9));
		cal.set(2009, Calendar.DECEMBER, 11);
		statsQueue.add(new UsageStatistics(cal.getTime(),10));
		
	}
	
	
	
	@Test
	@Ignore
	public void InterceptOnBaseArrayTest(){
		Assert.assertEquals(2.0,underTest.getIntercept(statsQueue));
	}
	
	@Test
	@Ignore
	public void SlopeOnBaseArrayTest(){
		Assert.assertEquals(2.0,underTest.getSlope(statsQueue));
	}
	
	@Test
	@Ignore
	public void DiffTestWithBaseValuesTest(){
		double intercept=underTest.getIntercept(statsQueue);
		Assert.assertEquals(intercept,underTest.getDiffToIntercept(statsQueue, intercept, 0));
	}
	
	@Test
	public void IsAlwaysIncreaseOnBaseArrayTest(){
		Assert.assertEquals(true,underTest.isAlwaysIncrease(statsQueue));
	}

}
