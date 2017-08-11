package com.dbp.main;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class mainSchedule {

	public static void main(String[] args) {
		System.out.println("初始化中……");
		init();
		System.out.println("每月1号凌晨0点执行，请稍等……");
	}

	public static void init() {
		try {
			JobDetail jobDetail = JobBuilder.newJob(mainJob.class).withIdentity("mainJob", "job-group").build();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger", "trigger-group").withSchedule(CronScheduleBuilder.cronSchedule("0 1 0 1 * ? *")).build();
			SchedulerFactory sFactory = new StdSchedulerFactory();
			Scheduler scheduler = sFactory.getScheduler();
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}