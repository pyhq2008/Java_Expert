package com.tingyun.SyncNtpTime;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/6/8.
 */
public class TestScheduleTask {

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(System.getProperty("tingyun.ntp-server"));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(new Runnable() {

            public void run() {
                System.out.println("I am running........");
            }
        }, 0, 10, TimeUnit.SECONDS);
        long timeoffset = 113557;
        System.out.println(timeoffset > 60 * 1000);
        TestScheduleTask t = new TestScheduleTask();

        System.out.println(t.getClass().getMethod("getName",new Class[0]).invoke(t,new Object[0]));
        Thread.sleep(20 * 1000);

        scheduledFuture.cancel(true);
        if(!scheduler.isShutdown()){
            scheduler.shutdownNow();
        }
    }




}
