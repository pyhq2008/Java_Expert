package com.baoxue.hotspot;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/6/17.
 */
public class TestSemaphore {



    public static void main(String[] args) {
            int N = 1000;            //工人数
            Semaphore semaphore = new Semaphore(1); //机器数目
            CountDownLatch countDownLatch = new CountDownLatch(N);
            for(int i=0;i<N;i++)
                new Worker(i,semaphore,countDownLatch).start();
    }

        static class Worker extends Thread{
            private int num;
            private Semaphore semaphore;
            private CountDownLatch countDownLatch;
            public Worker(int num,Semaphore semaphore,CountDownLatch countDownLatch){
                this.num = num;
                this.semaphore = semaphore;
                this.countDownLatch = countDownLatch;
            }

            @Override
            public void run() {
                try {
                    System.out.println("the thread "+this.num+" are countdown...");
                    countDownLatch.countDown();
                    countDownLatch.await();
                    if(semaphore.tryAcquire()){
                        System.out.println("the thread "+this.num+" get a semaphore...");
                        Thread.sleep(1000 * 60);
                        semaphore.release();
                    }
                    System.out.println("the thred "+this.num+"do not get a semaphoere....");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



}
