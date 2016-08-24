package com.baoxue.database.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * Created by Administrator on 2015/7/17.
 */
public class TestPipeline {

    public static Jedis jedis = new Jedis("127.0.0.1", 6379);


    @Test
       public void  testPipeline(){

        Pipeline pipeline = jedis.pipelined();
        pipeline.set("foo", "bar");
        pipeline.zadd("zaddkey", 1, "han");
        pipeline.zadd("zaddkey", 2, "quan");
        Response<String> pipeString = pipeline.get("foo");
        Response<Set<String>> sose = pipeline.zrange("zaddkey",0,-1);
        pipeline.sync();

        int soseSize = sose.get().size();
        Set<String> soseBack = sose.get();


    }


    @Test
    public void  testTransaction(){
        Transaction transaction = jedis.multi();
        transaction.set("foo","bar");
        Response<String> result1 = transaction.get("foo");
        transaction.set("foo", "bar");
        transaction.zadd("zaddkey", 1, "han");
        transaction.zadd("zaddkey", 2, "quan");
        Response<Set<String>> sose = transaction.zrange("zaddkey",0,-1);
        transaction.exec();

        String foobar = result1.get();
        int soseSize = sose.get().size();

    }

    public static void main(String[] args){


    }

}
