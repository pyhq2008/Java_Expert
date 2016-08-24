package com.baoxue.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by Administrator on 2016/7/14.
 */
public class TestLockSupport {

    public static void test(){
        LockSupport.park();
        System.out.println("block.");
    }

    public static void test2(){

        LockSupport.unpark(Thread.currentThread());//�ͷ����
        LockSupport.park();// ��ȡ���
        System.out.println("block.");
    }

    public static void test3(){
        Thread thread = Thread.currentThread();

        LockSupport.unpark(thread);
        System.out.println("a");
        LockSupport.park();
        System.out.println("b");
        LockSupport.park();
        System.out.println("c");
    }

    public static void test4() throws Exception
    {
        Thread t = new Thread(new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000)
                {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //�ȴ��������
                //�߳������Ϊ����park�������Ļ����ܹ���Ӧ�ж�����(�ж�״̬�����ó�true)��
                // ���ǲ����׳�InterruptedException
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(3000);

        // �ж��߳�
        t.interrupt();


        System.out.println("main over");
    }

    public static void main(String[] args)
    {
        try {
            test4();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
