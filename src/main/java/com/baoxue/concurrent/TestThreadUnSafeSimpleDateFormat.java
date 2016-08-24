package com.baoxue.concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/31.
 */
public class TestThreadUnSafeSimpleDateFormat {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParseDate implements Runnable{

        int i = 0;

        public ParseDate(int i){
            this.i = i;
        }
        @Override
        public void run() {
            try{
                Date date  = sdf.parse("2016-07-31 19:29:"+i%60);
                System.out.println(i + " : " + date);
            }catch (ParseException e){
              e.printStackTrace();
            }
        }
    }


    public static void main (String[] args){
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i< 1000; i++){
            es.execute(new ParseDate(i));
        }
    }
}
