/**
 * 
 */
package com.tingyun.quartz;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *
 * @author TianMingGang
 * 2015年7月30日
 */
public class TimerJob implements Job {

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
//			new DataDaoImpl().getDBError();
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			System.out.println(time + " 后台定时任务执行完成");
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
