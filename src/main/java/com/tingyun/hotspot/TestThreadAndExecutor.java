package com.tingyun.hotspot;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/4/28.
 */
public class TestThreadAndExecutor {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10000));

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for(int i=0;i < 10000;i++){
            Task task = new Task();
            Thread thread = new Thread(task);
            thread.start();
            thread.join();
        }
        System.out.println("execute time "+ (System.currentTimeMillis()-start));

        long start2 = System.currentTimeMillis();
        for(int i=0; i<10000; i++){
            Task task = new Task();
            threadPoolExecutor.submit(task);
        }
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
        while(true){
            if(threadPoolExecutor.getCompletedTaskCount() == 10000){
                System.out.println("execute time "+ (System.currentTimeMillis()-start2));
                break;
            }
        }

        System.out.println(threadPoolExecutor.getCompletedTaskCount());
//        System.out.println(executorService.isTerminated());
//        if(executorService.isShutdown()){
//            System.out.println("execute time "+ (System.currentTimeMillis()-start2));
//        }
//        System.out.println(executorService.isTerminated());

    }
}

class Task implements Runnable{

    @Override
    public void run() {
        Thread.currentThread().getStackTrace();
    }
}
