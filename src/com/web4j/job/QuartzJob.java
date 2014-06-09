package com.web4j.job;

import java.util.Date;

public class QuartzJob {

	private String jobName;
	private String jobGroup;
	private Date nextFireTime;
	private int status;
	
	public QuartzJob(){};

	public QuartzJob(String jobName, String jobGroup, Date nextFireTime,int status) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.nextFireTime = nextFireTime;
		this.status=status;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
