package com.acme.challenge.forecast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.UsageStatistics;

public class LeastSquare {

	public static double getSlope(PriorityQueue<UsageStatistics> statsQueue1) {
		double result = 0;
		SimpleRegression regression = new SimpleRegression();
		List<UsageStatistics> statsQueue=getSortedCollection(statsQueue1);
		int i = 0;
		for (UsageStatistics usageStatistics : statsQueue) {
			regression.addData(i, usageStatistics.getUsedVMs());
			i++;
		}
		result = regression.getSlope();
		return result;

	}

	public static double getIntercept(PriorityQueue<UsageStatistics> statsQueue1) {
		double result = 0;
		SimpleRegression regression = new SimpleRegression();
		List<UsageStatistics> statsQueue=getSortedCollection(statsQueue1);
		int i = 0;
		for (UsageStatistics usageStatistics : statsQueue) {
			regression.addData(i, usageStatistics.getUsedVMs());
			i++;
		}
		result = regression.getIntercept();
		return result;
	}

	public static double getDiffToIntercept(
			PriorityQueue<UsageStatistics> statsQueue1, double intercept,
			int index) {
		double result = 0;
		List<UsageStatistics> statsQueue=getSortedCollection(statsQueue1);
		double actualValue = statsQueue.get(index).getUsedVMs();
		result = actualValue - intercept;
		return result;
	}

	public static boolean isAlwaysIncrease(
			PriorityQueue<UsageStatistics> statsQueue1) {
		boolean result = true;
		Integer value = null;
		List<UsageStatistics> statsQueue=getSortedCollection(statsQueue1);
		for (UsageStatistics usageStatistics : statsQueue) {
			if (value == null) {
				value = usageStatistics.getUsedVMs();
			} else if (value > usageStatistics.getUsedVMs()) {
				result = false;
				value = usageStatistics.getUsedVMs();
				break;
				
			}
		}
		return result;
	}
	
	private static List<UsageStatistics> getSortedCollection(PriorityQueue<UsageStatistics> statsQueue1)
	{
		UsageStatistics[] statsQueueArray= (UsageStatistics[]) statsQueue1.toArray();
		List<UsageStatistics> statsQueue=Arrays.asList(statsQueueArray);
		Collections.sort(statsQueue);
		return statsQueue;
	}
}
