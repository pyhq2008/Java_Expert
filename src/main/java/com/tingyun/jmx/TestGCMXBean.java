package com.tingyun.jmx;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
public class TestGCMXBean {

    public static void main(String[] args){
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans){
            System.out.println("collection name :" + garbageCollectorMXBean.getName());
            System.out.println("collection count :" + garbageCollectorMXBean.getCollectionCount());
            System.out.println("collection name :" + garbageCollectorMXBean.getCollectionTime());

        }

    }
}
