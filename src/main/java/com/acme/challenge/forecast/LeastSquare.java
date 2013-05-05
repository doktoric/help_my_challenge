package com.acme.challenge.forecast;

import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.UsageStatistics;

public class LeastSquare {

	public static double getSlope(PriorityQueue<UsageStatistics> statsQueue) {
		double result = 0;
		SimpleRegression regression = new SimpleRegression();
		int i = 0;
		for (UsageStatistics usageStatistics : statsQueue) {
			regression.addData(i, usageStatistics.getUsedVMs());
			i++;
		}
		result = regression.getSlope();
		return result;

	}

	public static double getIntercept(PriorityQueue<UsageStatistics> statsQueue) {
		double result = 0;
		SimpleRegression regression = new SimpleRegression();
		int i = 0;
		for (UsageStatistics usageStatistics : statsQueue) {
			regression.addData(i, usageStatistics.getUsedVMs());
			i++;
		}
		result = regression.getIntercept();
		return result;
	}

	public static double getDiffToIntercept(
			PriorityQueue<UsageStatistics> statsQueue, double intercept,
			int index) {
		double result = 0;
		UsageStatistics[] values = new UsageStatistics[statsQueue.size()];
		statsQueue.toArray(values);
		double actualValue = values[index].getUsedVMs();
		result = actualValue - intercept;
		return result;
	}

	public static boolean isAlwaysIncrease(
			PriorityQueue<UsageStatistics> statsQueue) {
		boolean result = true;
		Integer value = null;
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
}
