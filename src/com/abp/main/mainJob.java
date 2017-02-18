package com.abp.main;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class mainJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		mainThread thread = new mainThread();
		try {
			thread.startMainThread();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}