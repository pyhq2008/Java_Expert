package com.baoxue.zookeeper.zkclient;
import com.baoxue.zookeeper.Constant;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;

// 使用ZkClient来创建一个ZooKeeper客户端
public class Create_Session_Sample {
    public static void main(String[] args) throws IOException, InterruptedException {
    	ZkClient zkClient = new ZkClient(Constant.ZK_SERVER_1, 5000);
    	System.out.println("ZooKeeper session established.");
    }
}