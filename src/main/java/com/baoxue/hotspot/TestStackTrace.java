package com.baoxue.hotspot;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/3/1.
 */
public class TestStackTrace {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        ThreadStack threadStack = new ThreadStack(Thread.currentThread(),semaphore);
        threadStack.start();

        ThreadStack threadStack2 = new ThreadStack(Thread.currentThread(),semaphore);
        threadStack2.start();

        System.out.println("test stackTrace");
        Thread.currentThread().sleep(10000L);


    }
}

class ThreadStack extends Thread{

    public Thread t;

    private Semaphore semaphore;

    public ThreadStack(Thread thread,Semaphore semaphore){
        this.t = thread;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        super.run();
        try {
            semaphore.acquire();
            StackTraceElement[] stackTraceElements = this.t.getStackTrace();
            for(StackTraceElement stackTraceElement : stackTraceElements){
                System.out.println("className:" + stackTraceElement.getClassName() + " | methodName:" + stackTraceElement.getMethodName());
            }
            System.out.println("-----------------------------------------------");
            StackTraceElement[] stackTraceElements2 = Thread.currentThread().getStackTrace();
            for(StackTraceElement stackTraceElement : stackTraceElements2){
                System.out.println("className:" + stackTraceElement.getClassName() + " | methodName:" + stackTraceElement.getMethodName());
            }
            Thread.currentThread().sleep(10000L);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class RunTerminated implements Runnable{

    private LinkedBlockingQueue stackBlockingQueue = new LinkedBlockingQueue<String>();

    public void run() {
        for(int i= 0 ; i < 20 ; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = String.valueOf(i);
            System.out.println("test  ------------------  "+ i);
        }
        System.out.println("game is over ..........");

    }
}