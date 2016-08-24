/**
 * 
 */
package com.tingyun.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author TianMingGang
 * 2015年7月30日
 */
public class TimeManager {
	
	public static void main(String[]args){
		new TimeManager().init();
	}
	
	public void init(){
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();

			JobDetail jobDetail = new JobDetail("timer", Scheduler.DEFAULT_GROUP, TimerJob.class);
			String cronExpression = getExpression();
			CronTrigger cronTrigger = new CronTrigger("timer", Scheduler.DEFAULT_GROUP, jobDetail.getName(),
		      Scheduler.DEFAULT_GROUP, cronExpression);
		    scheduler.addJob(jobDetail, true);
		    
		    scheduler.scheduleJob(cronTrigger);
		    scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getExpression(){
		String expression = "0 0/1 * * * ?";
//		String expression = "0/5 * * * * ?";
		return expression;
	}
}
