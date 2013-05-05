package com.acme.challenge;

import java.util.Date;
import java.util.PriorityQueue;

import junit.framework.Assert;

import org.junit.Before;
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
		statsQueue.add(new UsageStatistics(new Date(),0));
		statsQueue.add(new UsageStatistics(new Date(),1));
		statsQueue.add(new UsageStatistics(new Date(),2));
		statsQueue.add(new UsageStatistics(new Date(),3));
		statsQueue.add(new UsageStatistics(new Date(),4));
		statsQueue.add(new UsageStatistics(new Date(),5));
		statsQueue.add(new UsageStatistics(new Date(),6));
		statsQueue.add(new UsageStatistics(new Date(),7));
		statsQueue.add(new UsageStatistics(new Date(),8));
		statsQueue.add(new UsageStatistics(new Date(),9));
		statsQueue.add(new UsageStatistics(new Date(),10));
		
	}
	
	
	
//	@Test
//	public void InterceptOnBaseArrayTest(){
//		Assert.assertEquals(2.0,underTest.getIntercept(statsQueue));
//	}
//	
//	@Test
//	public void SlopeOnBaseArrayTest(){
//		Assert.assertEquals(2.0,underTest.getSlope(statsQueue));
//	}
//	
//	@Test
//	public void DiffTestWithBaseValuesTest(){
//		double intercept=underTest.getIntercept(statsQueue);
//		Assert.assertEquals(intercept,underTest.getDiffToIntercept(statsQueue, intercept, 0));
//	}
//	
	@Test
	public void IsAlwaysIncreaseOnBaseArrayTest(){
		Assert.assertEquals(true,underTest.isAlwaysIncrease(statsQueue));
	}

}
