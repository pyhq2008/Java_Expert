package com.baoxue.zookeeper.curator;

import com.baoxue.zookeeper.Constant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

//使用Curator获取数据内容
public class Get_Data_Sample {

    static String path = "/zk-book_curator";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(Constant.ZK_SERVER_1)
                    .sessionTimeoutMs(5000)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
              .creatingParentsIfNeeded()
              .withMode(CreateMode.EPHEMERAL)
              .forPath(path, "init".getBytes());
        Stat stat = new Stat();
        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
    }
}