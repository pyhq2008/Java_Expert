package com.baoxue.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2015/8/27.
 */
public class TestNativeOutOfMemoryError {
    public static void main(String[] args) {
        for (int i = 0;; i++) {
//            System.out.println("i ="+ i);
            new Thread(new HoldThread()).start();
        }

    }
}

class HoldThread extends Thread {
    CountDownLatch cdl = new CountDownLatch(1);

    public HoldThread() {
        this.setDaemon(true);
    }

    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
    }
}