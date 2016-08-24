package com.baoxue.concurrent;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ConcurrentTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                Thread.sleep(10*1000);
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get());

        countDownLatch.await();
    }
}
