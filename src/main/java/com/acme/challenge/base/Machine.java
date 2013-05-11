package com.acme.challenge.base;

import java.util.Date;

public class Machine {

	private static final int BILLING_UNIT_IN_MILLISEC = 3600000;

	private Date activeFrom;

	public Machine() {
	}

	public Date getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}

	public Machine(Date activeFrom) {
		this.activeFrom = activeFrom;
	}

	public int secondsTillBilling(Date now){
		long millisecondsFromLastBilling = (now.getTime() - activeFrom.getTime()) % BILLING_UNIT_IN_MILLISEC;
		long millisecondsTillNextBilling = BILLING_UNIT_IN_MILLISEC - millisecondsFromLastBilling;
		return (int) (millisecondsTillNextBilling / 1000);
		
	}

}
