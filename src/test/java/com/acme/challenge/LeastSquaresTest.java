package com.acme.challenge;

import java.util.Calendar;
import java.util.PriorityQueue;

import junit.framework.Assert;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Before;
import org.junit.Test;

import com.acme.challenge.base.UsageStatistics;
import com.acme.challenge.forecast.LeastSquares;

public class LeastSquaresTest {

	private LeastSquares underTest;
	private PriorityQueue<UsageStatistics> statsQueue;
	
	@Before
	public void setUp(){
		underTest=new LeastSquares();
		statsQueue=new PriorityQueue<UsageStatistics>();
		Calendar cal = Calendar.getInstance();
		cal.set(2009, Calendar.DECEMBER, 7);
		statsQueue.add(new UsageStatistics(cal.getTime(),8));
		cal.set(2009, Calendar.DECEMBER, 3);
		statsQueue.add(new UsageStatistics(cal.getTime(),4));
		cal.set(2009, Calendar.DECEMBER, 4);
		statsQueue.add(new UsageStatistics(cal.getTime(),5));
		cal.set(2009, Calendar.DECEMBER, 6);
		statsQueue.add(new UsageStatistics(cal.getTime(),7));
		cal.set(2009, Calendar.DECEMBER, 5);
		statsQueue.add(new UsageStatistics(cal.getTime(),6));
		cal.set(2009, Calendar.DECEMBER, 1);
		statsQueue.add(new UsageStatistics(cal.getTime(),2));
		cal.set(2009, Calendar.DECEMBER, 10);
		statsQueue.add(new UsageStatistics(cal.getTime(),11));
		cal.set(2009, Calendar.DECEMBER, 8);
		statsQueue.add(new UsageStatistics(cal.getTime(),9));
		cal.set(2009, Calendar.DECEMBER, 9);
		statsQueue.add(new UsageStatistics(cal.getTime(),10));
		cal.set(2009, Calendar.DECEMBER, 2);
		statsQueue.add(new UsageStatistics(cal.getTime(),3));
		
		//2,3,4,5,6,7,8,9,10,11
	}
	
	
	@Test
	public void testInterceptCalculation(){
		SimpleRegression regression = underTest.createRegression(statsQueue);
		Assert.assertEquals(2.0,underTest.getIntercept(regression));
	}
	
	@Test
	public void testSlopeCalculation(){
		SimpleRegression regression = underTest.createRegression(statsQueue);
		Assert.assertEquals(1.0,underTest.getSlope(regression));
	}
	
	@Test
	public void testPrediction(){		
		SimpleRegression regression = underTest.createRegression(statsQueue);
		Assert.assertEquals(13.0,underTest.getForecast(regression, 11.0));
	}
	
	@Test
	public void testSlopeStandardError(){
		SimpleRegression regression = underTest.createRegression(statsQueue);
		Assert.assertEquals(0.0,underTest.getSlopeStdError(regression));
	}
	

//	
//	@Test
//	@Ignore
//	public void DiffTestWithBaseValuesTest(){
//		double intercept=underTest.getIntercept(statsQueue);
//		Assert.assertEquals(intercept,underTest.getDiffToIntercept(statsQueue, intercept, 0));
//	}
//	
//	@Test
//	public void IsAlwaysIncreaseOnBaseArrayTest(){
//		Assert.assertEquals(true,underTest.isAlwaysIncrease(statsQueue));
//	}

}
