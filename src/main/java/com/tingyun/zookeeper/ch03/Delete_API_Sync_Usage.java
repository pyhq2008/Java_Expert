package com.tingyun.zookeeper.ch03;
import com.tingyun.zookeeper.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// ZooKeeper API 删除节点，使用同步(sync)接口。
public class Delete_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {

    	String path = "/zk-book";
    	zk = new ZooKeeper(Constant.ZK_SERVER_1,
				5000, //
				new Delete_API_Sync_Usage());
    	connectedSemaphore.await();

    	zk.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        TimeUnit.SECONDS.sleep(5);
    	zk.delete( path, -1 );
    	
    	Thread.sleep( Integer.MAX_VALUE );
    }
    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
//            else if(EventType.NodeCreated == event.getType()){
//                System.out.println("create :"+event.getPath());
//            }
        }
    }
}