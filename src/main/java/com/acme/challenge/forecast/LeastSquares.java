package com.acme.challenge.forecast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.UsageStatistics;

public class LeastSquares {

	private LeastSquares() {
	}

	private static class LeastSquaresHolder {
		public static final LeastSquares INSTANCE = new LeastSquares();
	}

	public static LeastSquares getInstance() {
		return LeastSquaresHolder.INSTANCE;
	}

	public SimpleRegression createRegression(List<UsageStatistics> statsQueue) {
		SimpleRegression regression = new SimpleRegression();
		int i = 0;
		for (UsageStatistics usageStatistics : statsQueue) {
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

	private List<Double> reverseList(List<Double> myList) {
		List<Double> invertedList = new ArrayList<Double>();
		for (int i = myList.size() - 1; i >= 0; i--) {
			invertedList.add(myList.get(i));
		}
		return invertedList;
	}

	public double getForecast(SimpleRegression regression, double x) {
		return regression.predict(x);
	}

	public double getSlopeStdError(SimpleRegression regression) {
		return regression.getSlopeStdErr();
	}

}

