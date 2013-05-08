package com.acme.challenge.base;

import java.util.Date;

public class Machine {

	private Date activeFrom;

	public Date getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}

	public Machine(Date activeFrom) {
		this.activeFrom = activeFrom;
	}

	public Machine() {
	}

}
