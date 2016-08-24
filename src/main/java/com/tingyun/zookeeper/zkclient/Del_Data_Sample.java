package com.tingyun.zookeeper.zkclient;

import com.tingyun.zookeeper.Constant;
import org.I0Itec.zkclient.ZkClient;

//ZkClient删除节点数据
public class Del_Data_Sample {
	public static void main(String[] args) throws Exception {
		String path = "/zk-book";
    	ZkClient zkClient = new ZkClient(Constant.ZK_SERVER_1, 2000);
//        zkClient.createPersistent(path, "");
//        zkClient.createPersistent(path+"/c1", "");
        zkClient.deleteRecursive(path);
    }
}