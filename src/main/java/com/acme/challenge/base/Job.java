package com.acme.challenge.base;

import java.util.Date;

public class Job {

    private Date dateTime;
    private String id;
    private QueueType queueType;
    private double runtimeInSeconds;
    private String jobOutput;

    public Job() {
    }

    public Job(Date dateTime, String dateString, String id, QueueType queueType, double runtimeInSeconds, String jobOutput) {
        super();
        this.dateTime = dateTime;
        this.id = id;
        this.queueType = queueType;
        this.runtimeInSeconds = runtimeInSeconds;
        this.jobOutput = jobOutput;
    }

    @Override
    public String toString() {
        return "Job [dateTime=" + dateTime + ", id=" + id + ", queueType=" + queueType + ", runtimeInSeconds=" + runtimeInSeconds + "]";
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QueueType getQueueType() {
        return queueType;
    }

    public void setQueueType(QueueType queueType) {
        this.queueType = queueType;
    }

    public double getRuntimeInSeconds() {
        return runtimeInSeconds;
    }

    public void setRuntimeInSeconds(double runtimeInSeconds) {
        this.runtimeInSeconds = runtimeInSeconds;
    }
    
    public String getJobOutput() {
		return jobOutput;
	}
    
    public void setJobOutput(String jobOutput) {
		this.jobOutput = jobOutput;
	}
}
