package com.acme.challenge.simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acme.challenge.base.UsageStatistics;

public class VirtualLoadSimulatorTest {

	
	private VirtualLoadSimulator underTest;
	
	@Before
	public void setUp(){
		underTest = VirtualLoadSimulator.getInstance();
		PriorityQueue<UsageStatistics> statsQueue=new PriorityQueue<UsageStatistics>();
		Calendar cal = Calendar.getInstance();
		cal.set(2009, Calendar.DECEMBER, 8);
		statsQueue.add(new UsageStatistics(cal.getTime(),9));
		cal.set(2009, Calendar.DECEMBER, 3);
		statsQueue.add(new UsageStatistics(cal.getTime(),4));
		cal.set(2009, Calendar.DECEMBER, 5);
		statsQueue.add(new UsageStatistics(cal.getTime(),6));
		cal.set(2009, Calendar.DECEMBER, 9);
		statsQueue.add(new UsageStatistics(cal.getTime(),10));
		cal.set(2009, Calendar.DECEMBER, 1);
		statsQueue.add(new UsageStatistics(cal.getTime(),2));
		cal.set(2009, Calendar.DECEMBER, 6);
		statsQueue.add(new UsageStatistics(cal.getTime(),7));
		cal.set(2009, Calendar.DECEMBER, 10);
		statsQueue.add(new UsageStatistics(cal.getTime(),11));
		cal.set(2009, Calendar.DECEMBER, 7);
		statsQueue.add(new UsageStatistics(cal.getTime(),8));
		cal.set(2009, Calendar.DECEMBER, 4);
		statsQueue.add(new UsageStatistics(cal.getTime(),5));
		cal.set(2009, Calendar.DECEMBER, 2);
		statsQueue.add(new UsageStatistics(cal.getTime(),3));
		underTest.setUrlStatsQueue(statsQueue);
	}
	
	@Test
	public void testGetUrlStatsQueueSorted(){
		List<UsageStatistics> result = underTest.getUrlStatsQueueSorted();
		Assert.assertEquals(2, (int)result.get(0).getUsedVMs());
		Assert.assertEquals(3, (int)result.get(1).getUsedVMs());
		Assert.assertEquals(4, (int)result.get(2).getUsedVMs());
		Assert.assertEquals(5, (int)result.get(3).getUsedVMs());
		Assert.assertEquals(6, (int)result.get(4).getUsedVMs());
		Assert.assertEquals(7, (int)result.get(5).getUsedVMs());
		Assert.assertEquals(8, (int)result.get(6).getUsedVMs());
		Assert.assertEquals(9, (int)result.get(7).getUsedVMs());
		Assert.assertEquals(10, (int)result.get(8).getUsedVMs());
		Assert.assertEquals(11, (int)result.get(9).getUsedVMs());
	}
	
	@Test
	public void getLastNOfUrlStatsQueueSorted(){
		List<UsageStatistics> result = underTest.getLastNOfUrlStatsQueueSorted(3);
		Assert.assertEquals(9, (int)result.get(0).getUsedVMs());
		Assert.assertEquals(10, (int)result.get(1).getUsedVMs());
		Assert.assertEquals(11, (int)result.get(2).getUsedVMs());
	}
	
	
}
