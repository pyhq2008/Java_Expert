package com.tingyun.database.memcache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * Created by Administrator on 2016/4/11.
 */
public class MemcacheTest {


    public static void main(String[] args){
        MemCachedClient memCachedClient ;
        String[] servers = { "192.168.2.130:11211" };
        Integer[] weights = {3, 2};

        // ����һ��Socked���ӳ�ʵ��
        SockIOPool pool = SockIOPool.getInstance();
        // �����ӳ����÷�������Ȩ��
        pool.setServers(servers);
        pool.setWeights(weights);

        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);
        pool.initialize();
        memCachedClient = new MemCachedClient();

        memCachedClient.add("foo", "add foo");

        System.out.println(memCachedClient.get("foo"));
    }
}
