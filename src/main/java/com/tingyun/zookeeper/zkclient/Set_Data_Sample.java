package com.tingyun.zookeeper.zkclient;

import com.tingyun.zookeeper.Constant;
import org.I0Itec.zkclient.ZkClient;

//ZkClient更新节点数据
public class Set_Data_Sample {

    public static void main(String[] args) throws Exception {
    	String path = "/zk-book";
    	ZkClient zkClient = new ZkClient(Constant.ZK_SERVER_1, 2000);
        zkClient.createEphemeral(path, new Integer(1));
        zkClient.writeData(path, new Integer(1));
    }
}