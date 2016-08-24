package com.baoxue.database.redis.springredis;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/7/13.
 */
public class PipelineTest {


    public static void main(String[] args){
        int count = 1000;
        long start = System.currentTimeMillis();
        withoutPipeline(count);
        long end = System.currentTimeMillis();
        System.out.println("withoutPipeline: " + (end-start));

        start = System.currentTimeMillis();
        usePipeline(count);
        end = System.currentTimeMillis();
        System.out.println("usePipeline: " + (end-start));
    }




    private static void withoutPipeline(int count){

        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379);
            System.out.println(jr.getClass().getGenericSuperclass());
            Field client = jr.getClass().getSuperclass().getDeclaredField("client");
            client.setAccessible(true);
            Client dd = (Client)client.get(jr);
            long start = System.currentTimeMillis();
            System.out.println(start);
            for(int i =0; i<count; i++){
                jr.incr("testKey1");
            }
            Field redisOutputStream = dd.getClass().getSuperclass().getSuperclass().getDeclaredField("outputStream");
            redisOutputStream.setAccessible(true);
            Field buf = redisOutputStream.get(dd).getClass().getDeclaredField("buf");
            buf.setAccessible(true);
            byte[] aa = (byte[])buf.get(redisOutputStream.get(dd));
            System.out.println(new String(aa,"utf-8"));


            System.out.println(System.currentTimeMillis() -start);
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally{
            if(jr!=null){
                jr.disconnect();
            }

        }

    }



    private static void usePipeline(int count){

//        Jedis jr = null;
//        try {
//            jr = new Jedis("127.0.0.1", 6379);
//            Pipeline pl = jr.pipelined();
//            for(int i =0; i<count; i++){
//                pl.incr("testKey2");
//            }
//            pl.sync();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        finally{
//
//            if(jr!=null){
//
//                jr.disconnect();
//
//            }
//
//        }

    }
}


