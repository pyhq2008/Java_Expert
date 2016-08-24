package com.baoxue.hotspot;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/3/1.
 */
public class ActionTest {

    public static Semaphore semaphore = new Semaphore(1);

    public StackTraceElement[] stackTraceElements;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


//        final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate( new ThreadStack(Thread.currentThread(),semaphore), 0, 10, TimeUnit.SECONDS);

//        final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(new RunTerminated(),0,100,TimeUnit.SECONDS);
//
//        Thread.sleep(1000);
//        scheduler.shutdownNow();



          System.out.println(System.currentTimeMillis());
          StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
          System.out.println(System.currentTimeMillis());





    }
}
