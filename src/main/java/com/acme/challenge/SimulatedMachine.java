package com.acme.challenge;

import java.util.Date;

public class SimulatedMachine {

    private Date activeFrom;
    private Date busyTill;

    public SimulatedMachine() {
    }

    public SimulatedMachine(Date activeFrom, Date busyTill) {
        super();
        this.activeFrom = activeFrom;
        this.busyTill = busyTill;
    }

    @Override
    public String toString() {
        return "SimulatedMachine [activeFrom=" + activeFrom + ", busyTill=" + busyTill + "]";
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getBusyTill() {
        return busyTill;
    }

    public void setBusyTill(Date busyTill) {
        this.busyTill = busyTill;
    }

    public boolean isBusy(Date date) {
        return this.busyTill.getTime() >= date.getTime();
    }

}
