package com.acme.challenge.model;

import static com.acme.challenge.SimulatedMachineManager.MAX_QUEUE_SIZE;
import static com.acme.challenge.SimulatedMachineManager.MAX_USAGE;
import static com.acme.challenge.SimulatedMachineManager.MIN_USAGE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.acme.challenge.OutputWriter;
import com.acme.challenge.base.Command;
import com.acme.challenge.base.Job;
import com.acme.challenge.base.QueueType;
import com.acme.challenge.base.UsageStatistics;

public  class UrlMachineStrategy extends MachineStrategy {


	private UrlMachineStrategy(QueueType type) {
		
		this.type = type;
	}


	public static MachineStrategy urlMachineStrategy(QueueType type) {
		return new UrlMachineStrategy(type);
	}

}
