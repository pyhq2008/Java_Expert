package com.tingyun.zookeeper.zkclient;
import com.tingyun.zookeeper.Constant;
import org.I0Itec.zkclient.ZkClient;

// 使用ZkClient创建节点
public class Create_Node_Sample {

    public static void main(String[] args) throws Exception {
    	ZkClient zkClient = new ZkClient(Constant.ZK_SERVER_1, 5000);
        String path = "/zk-book/c1";
        zkClient.createPersistent(path, true);
    }
}