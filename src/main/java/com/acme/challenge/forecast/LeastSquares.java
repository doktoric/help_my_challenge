package com.acme.challenge.forecast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.UsageStatistics;

public class LeastSquares {

	public SimpleRegression createRegression(PriorityQueue<UsageStatistics> statsQueue) {
		SimpleRegression regression = new SimpleRegression();
		List<UsageStatistics> statsList = getSortedCollection(statsQueue);
		int i = 0;
		for (UsageStatistics usageStatistics : statsList) {
//			System.out.println("added data: " + i + " " + usageStatistics.getUsedVMs());
			regression.addData(i, usageStatistics.getUsedVMs());
			i++;
		}
		return regression;
	}

	public double getSlope(SimpleRegression regression) {
		return regression.getSlope();
	}

	public double getIntercept(SimpleRegression regression) {
		return regression.getIntercept();
	}
	
	public double getForecast(SimpleRegression regression, double x) {
		return regression.predict(x);
	}
	
	public double getSlopeStdError(SimpleRegression regression) {
		return regression.getSlopeStdErr();
	}
	

//	public static double getDiffToIntercept(
//			PriorityQueue<UsageStatistics> statsQueue1, double intercept,
//			int index) {
//		double result = 0;
//		List<UsageStatistics> statsQueue = getSortedCollection(statsQueue1);
//		double actualValue = statsQueue.get(index).getUsedVMs();
//		result = actualValue - intercept;
//		return result;
//	}
//
//	public static boolean isAlwaysIncrease(
//			PriorityQueue<UsageStatistics> statsQueue1) {
//		boolean result = true;
//		Integer value = null;
//		List<UsageStatistics> statsQueue = getSortedCollection(statsQueue1);
//		for (UsageStatistics usageStatistics : statsQueue) {
//			if (value == null) {
//				value = usageStatistics.getUsedVMs();
//			} else if (value > usageStatistics.getUsedVMs()) {
//				result = false;
//				value = usageStatistics.getUsedVMs();
//				break;
//
//			}
//		}
//		return result;
//	}

	private List<UsageStatistics> getSortedCollection(PriorityQueue<UsageStatistics> statsQueue1) {
		List<UsageStatistics> statsList = new ArrayList<UsageStatistics>(statsQueue1);
		Collections.sort(statsList);
		return statsList;
	}
}
