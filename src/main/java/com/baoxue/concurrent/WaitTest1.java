package com.baoxue.concurrent;

/**
 * Created by Administrator on 2016/7/15.
 */
 public class WaitTest1 {

     public static void main(String[] args) {

          ThreadA ta = new ThreadA("ta");

            synchronized(ta) { // ͨ��synchronized(ta)��ȡ������ta��ͬ������
                try {
                    System.out.println(Thread.currentThread().getName()+" start ta");
                    ta.start();
                    System.out.println(Thread.currentThread().getName()+" block");
                    // ���̵߳ȴ�
                    ta.wait();
                    System.out.println(Thread.currentThread().getName()+" continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             }
       }

       static class ThreadA extends Thread{

           public ThreadA(String name) {
                         super(name);
           }
           public void run() {
               synchronized (this) { // ͨ��synchronized(this)��ȡ����ǰ�����ͬ������
                   System.out.println(Thread.currentThread().getName()+" wakup others");
                   notify();    // ���ѡ���ǰ�����ϵĵȴ��̡߳�
               }
           }
       }
 }