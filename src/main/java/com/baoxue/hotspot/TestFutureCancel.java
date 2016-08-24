package com.baoxue.hotspot;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TestFutureCancel {



    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        LinkedBlockingQueue<String> stackBlockingQueue = new LinkedBlockingQueue<String>();
//        Future future = scheduler.scheduleAtFixedRate(new Producer("run1",stackBlockingQueue), 0L, 1500L, TimeUnit.MILLISECONDS);
        Future future = scheduler.scheduleAtFixedRate(new Producer("run2", stackBlockingQueue), 0L, 50L, TimeUnit.MILLISECONDS);
        Thread consumer = new Thread(new Consumer("consumer",stackBlockingQueue));
        consumer.start();
        Thread.sleep(30 * 1000);
        future.cancel(true);

        Future future2 = scheduler.scheduleAtFixedRate(new Producer("run3", stackBlockingQueue), 0L, 50L, TimeUnit.MILLISECONDS);
        Thread.sleep(30 * 1000);
        future2.cancel(true);

        Future future3 = scheduler.scheduleAtFixedRate(new Producer("run4", stackBlockingQueue), 0L, 50L, TimeUnit.MILLISECONDS);
        Thread.sleep(30 * 1000);
        future2.cancel(true);

        Future future4 = scheduler.scheduleAtFixedRate(new Producer("run4", stackBlockingQueue), 0L, 50L, TimeUnit.MILLISECONDS);
        Thread.sleep(30 * 1000);
        future2.cancel(true);


        consumer.join();
//        future.cancel(false);



    }



}


class Producer implements Runnable{

    private String name;

    private LinkedBlockingQueue<String> stackBlockingQueue = new LinkedBlockingQueue<String>();

    Producer(String name, LinkedBlockingQueue stackBlockingQueue){
       this.name = name;
        this.stackBlockingQueue = stackBlockingQueue;
    }

    public void run() {
        String str = Thread.getAllStackTraces().toString();
        this.stackBlockingQueue.offer(str);
    }
}


class Consumer implements Runnable{

    private String name;

    private LinkedBlockingQueue<String> stackBlockingQueue = new LinkedBlockingQueue<String>();

    Consumer(String name, LinkedBlockingQueue stackBlockingQueue){
        this.name = name;
        this.stackBlockingQueue = stackBlockingQueue;
    }

    @Override
    public void run() {
        while (true){
            while(stackBlockingQueue.size() > 0){
                try {
                    Thread.sleep(100L);
                    System.out.println(stackBlockingQueue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}