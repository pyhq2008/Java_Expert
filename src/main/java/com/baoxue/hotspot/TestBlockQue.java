package com.baoxue.hotspot;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2016/3/9.
 */
public class TestBlockQue {

    public static void main(String[] args){
       LinkedBlockingQueue<String> stackBlockingQueue = new LinkedBlockingQueue<String>();
        for(int i=0;i < 5 ; i++){
            stackBlockingQueue.add("aaa" + i);
        }
        Object[] oo = stackBlockingQueue.toArray();

        System.out.println(oo.length);
        System.out.println(stackBlockingQueue.size());


    }
}
