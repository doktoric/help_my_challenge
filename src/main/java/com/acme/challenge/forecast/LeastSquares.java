package com.acme.challenge.forecast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.acme.challenge.base.UsageStatistics;

public class LeastSquares {

	public SimpleRegression createRegression(
			PriorityQueue<UsageStatistics> statsQueue) {
		SimpleRegression regression = new SimpleRegression();
		List<UsageStatistics> statsList = getSortedCollection(statsQueue);
		int i = 0;
		for (UsageStatistics usageStatistics : statsList) {
			// System.out.println("added data: " + i + " " +
			// usageStatistics.getUsedVMs());
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

	public double getForecastMultiplyer(
			PriorityQueue<UsageStatistics> statsQueue, int separator) {
		double multiplier = 1;
		List<UsageStatistics> statsList = getSortedCollection(statsQueue);
		List<Double> rates = new ArrayList<Double>();
		
		//szétbontja szegmensekre az adott statisztikákat
		if (statsList.size() > separator) {
			for (int i = 0; i < statsList.size(); i = i + separator) {
				//minden szegmensnek kiszámolja az átlagát
				rates.add(getSegmentMiddleValue(statsList.subList(i, i
						+ separator)));
			}
			//2,2,4,8,32,
			List<Double> ratesBetweenElements = new ArrayList<Double>();
			for (int i = 0; i < rates.size()-1; i++) {
				//összerakja az átlagértékek közti arányt
				ratesBetweenElements.add(rates.get(i)/rates.get(i+1));
			}
			//1,2,2,3
			List<Double> series=getMostLongSeries(ratesBetweenElements);
			//2,3
			//1,3,4,8,12
			//3,1.25,2,1.33
			double sum=0;
			for (int i = 0; i < series.size(); i++) {
				sum+=series.get(i);
			}
			multiplier=sum/series.size();	
		}
		
		

		return multiplier;

	}

	private List<Double> getMostLongSeries(List<Double> values) {
		List<Double> returnSerie = new ArrayList<Double>();
		Boolean isDec = null;
		for (int i = values.size() - 2; i >= 0; i--) {
			if (isDec == null) {
				returnSerie.add(values.get(i+1));
				returnSerie.add(values.get(i));
				if (values.get(i) < values.get(i + 1)) {
					isDec= new Boolean(true);
				}
			}
			else if(!isDec){
				if(values.get(i) > values.get(i + 1)){
					returnSerie.add(values.get(i));
				}
				else{
					break;
				}
			}
			else if(isDec){
				if(values.get(i) < values.get(i + 1)){
					returnSerie.add(values.get(i));
				}
				else{
					break;
				}
			}
		}

		return returnSerie;

	}

	private double getSegmentMiddleValue(List<UsageStatistics> statistics) {
		double value = 0;
		double sum = 0;
		for (UsageStatistics usageStatistics : statistics) {
			sum += usageStatistics.getUsedVMs();
		}
		value = sum / statistics.size();
		return value;
	}

	public double getForecast(SimpleRegression regression, double x) {
		return regression.predict(x);
	}

	public double getSlopeStdError(SimpleRegression regression) {
		return regression.getSlopeStdErr();
	}

	// public static double getDiffToIntercept(
	// PriorityQueue<UsageStatistics> statsQueue1, double intercept,
	// int index) {
	// double result = 0;
	// List<UsageStatistics> statsQueue = getSortedCollection(statsQueue1);
	// double actualValue = statsQueue.get(index).getUsedVMs();
	// result = actualValue - intercept;
	// return result;
	// }
	//
	// public static boolean isAlwaysIncrease(
	// PriorityQueue<UsageStatistics> statsQueue1) {
	// boolean result = true;
	// Integer value = null;
	// List<UsageStatistics> statsQueue = getSortedCollection(statsQueue1);
	// for (UsageStatistics usageStatistics : statsQueue) {
	// if (value == null) {
	// value = usageStatistics.getUsedVMs();
	// } else if (value > usageStatistics.getUsedVMs()) {
	// result = false;
	// value = usageStatistics.getUsedVMs();
	// break;
	//
	// }
	// }
	// return result;
	// }

	private List<UsageStatistics> getSortedCollection(
			PriorityQueue<UsageStatistics> statsQueue1) {
		List<UsageStatistics> statsList = new ArrayList<UsageStatistics>(
				statsQueue1);
		Collections.sort(statsList);
		return statsList;
	}
}
