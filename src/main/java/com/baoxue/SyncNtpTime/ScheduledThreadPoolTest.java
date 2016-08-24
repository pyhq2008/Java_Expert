package com.baoxue.SyncNtpTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/6/10.
 */
public class ScheduledThreadPoolTest {

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd dd:mm:ss");

    /**
     * beepForTime
     */
    public static void beepForTime() {

        /**
         * Ҫִ�е�����
         */
        final Runnable beeper = new Runnable() {
            int i=0;
            public void run() {
                System.out.println("beep:"+(i++)+"----"+sf.format(new Date(System.currentTimeMillis())));
                //throw new RuntimeException();

                //�����ʱ2��
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        int startTime=3;
        int each=5;
        System.out.println("������"+startTime+"���ʼ��ÿ"+each+"��ִ��1��");

        /**
         * ÿeach��ִ��һ���������������ִ�м����each�루ǰ�涨��ı����
         *
         *
         * ������ִ��һ���ڸ�����ʼ�ӳٺ��״����õĶ��ڲ����������������и��������ڣ�
         * Ҳ���ǽ��� initialDelay ��ʼִ�У�Ȼ���� initialDelay+period ��ִ�У�
         * ������ initialDelay + 2 * period ��ִ�У��������ơ����������κ�һ��ִ�������쳣��
         * �����ִ�ж��ᱻȡ��������ֻ��ͨ��ִ�г����ȡ������ֹ��������ֹ������
         * �����������κ�һ��ִ��Ҫ���ѱ������ڸ�����ʱ�䣬���Ƴٺ���ִ�У�������ͬʱִ�С�
         * ������
         * command - Ҫִ�е�����
         * initialDelay - �״�ִ�е��ӳ�ʱ��
         * period - ����ִ��֮�������
         * unit - initialDelay �� period ������ʱ�䵥λ
         *
         */
        //final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, startTime, each, TimeUnit.SECONDS);


        /**
         *
         * ÿeach��ִ��һ���������������ִ�м����each�루ǰ�涨��ı����+����ִ����ʱ
         *
         * ������ִ��һ���ڸ�����ʼ�ӳٺ��״����õĶ��ڲ�����
         * �����ÿһ��ִ����ֹ����һ��ִ�п�ʼ֮�䶼���ڸ������ӳ١�
         * ����������һִ�������쳣���ͻ�ȡ������ִ�С�
         * ����ֻ��ͨ��ִ�г����ȡ������ֹ��������ֹ������
         * ������
         * command - Ҫִ�е�����
         * initialDelay - �״�ִ�е��ӳ�ʱ��
         * period - ����ִ��֮�������
         * unit - initialDelay �� period ������ʱ�䵥λ
         *
         */
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleWithFixedDelay(beeper, startTime, each, TimeUnit.SECONDS);

        /**
         * ������ִ���ڸ����ӳٺ����õ�һ���Բ�����
         *
         * ����������Nʱ���ȡ������
         */
        scheduler.schedule(new Runnable() {
            public void run() {
                System.out.println("ȡ������");
                beeperHandle.cancel(true);
            }
        }, 60, TimeUnit.SECONDS);
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        beepForTime();
    }

}
