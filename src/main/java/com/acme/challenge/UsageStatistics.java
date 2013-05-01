package com.acme.challenge;

import java.util.Date;

public class UsageStatistics implements Comparable<UsageStatistics> {
	
	private Date second;
	private Integer usedVMs;
	
	public UsageStatistics() {
	}
	
	public UsageStatistics(Date second, Integer usedVMs) {
		super();
		this.second = second;
		this.usedVMs = usedVMs;
	}

	@Override
	public int compareTo(UsageStatistics o) {
		if (this.second.after(o.second))
            return 1;
        else
            return -1;
	}

	@Override
	public String toString() {
		return "UsageStatistics [second=" + second + ", usedVMs=" + usedVMs
				+ "]";
	}
	
	public Date getSecond() {
		return second;
	}
	
	public Integer getUsedVMs() {
		return usedVMs;
	}
	
}
