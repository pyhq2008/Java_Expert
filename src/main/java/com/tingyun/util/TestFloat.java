package com.tingyun.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/3.
 */
public class TestFloat {


    public static void main(String[] args){

        long ll = 123456L;
        float a = 1000000.0f;
        long c = 10000000L;

        float b = ll;
        System.out.println(b);
        System.out.println(ll / a);
        System.out.println((float)(ll / c));


        long millsencond = 1000L;

        System.out.println(TimeUnit.NANOSECONDS.convert(millsencond,TimeUnit.MILLISECONDS));
    }
}
