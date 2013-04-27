package com.acme.challenge;

import java.util.Date;

public class Job {

    private Date dateTime;
    private String id;
    private String queueType;
    private double runtimeInSeconds;

    public Job() {
    }

    public Job(Date dateTime, String dateString, String id, String queueType, double runtimeInSeconds) {
        super();
        this.dateTime = dateTime;
        this.id = id;
        this.queueType = queueType;
        this.runtimeInSeconds = runtimeInSeconds;
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

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public double getRuntimeInSeconds() {
        return runtimeInSeconds;
    }

    public void setRuntimeInSeconds(double runtimeInSeconds) {
        this.runtimeInSeconds = runtimeInSeconds;
    }

}
