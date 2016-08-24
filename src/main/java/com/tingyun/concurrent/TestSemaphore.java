package com.tingyun.concurrent;

/**
 * Created by Administrator on 2016/3/1.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * �����ź�����ʹ��
 *
 */
public class TestSemaphore {


    public  static  void testAcquire(){
        // �̳߳�
        ExecutorService exec = Executors.newCachedThreadPool();
        // ֻ��5���߳�ͬʱ����
        final Semaphore semp = new Semaphore(5);
        // ģ��20���ͻ��˷���
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // ��ȡ���
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // ��������ͷ�
                        semp.release();
                        //availablePermits()ָ���ǵ�ǰ�źŵƿ����ж��ٸ����Ա�ʹ��
                        System.out.println("-----------------" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // �˳��̳߳�
        exec.shutdown();
    }

    public  static  void  testTryAcquire(){
        // �̳߳�
        ExecutorService exec = Executors.newCachedThreadPool();
        // ֻ��5���߳�ͬʱ����
        final Semaphore semp = new Semaphore(5);
        // ģ��20���ͻ��˷���
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        /***���Ż�ȡ���:
                         *   1. ��ȡִ��
                         *   2. δ��ȡ������
                          */
                       if(semp.tryAcquire()){
                           System.out.println("Accessing: " + NO);
                           Thread.sleep((long) (Math.random() * 6000));
                           // ��������ͷ�
                           semp.release();
                       }else{
                           System.out.println("No Accessing: " + NO + "but I can do the next ");
                       }
                        //availablePermits()ָ���ǵ�ǰ�źŵƿ����ж��ٸ����Ա�ʹ��
                        System.out.println("-----------------" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // �˳��̳߳�
        exec.shutdown();
    }


    public static void main(String[] args) {
        testTryAcquire();
    }
}