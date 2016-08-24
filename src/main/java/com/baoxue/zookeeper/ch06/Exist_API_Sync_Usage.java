package com.baoxue.zookeeper.ch06;
import com.baoxue.zookeeper.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

// ZooKeeper API 删除节点，使用同步(sync)接口。
public class Exist_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;
    public static void main(String[] args) throws Exception {

    	String path = "/zk-book";
    	zk = new ZooKeeper(Constant.ZK_SERVER_1,
				5000, //
				new Exist_API_Sync_Usage());
    	connectedSemaphore.await();

        //只监控当前path的创建删除，不监控子节点，也不监控数据变更
    	zk.exists( path, true );
    	
    	zk.create( path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT );
    	
    	zk.setData( path, "123".getBytes(), -1 );
    	
    	zk.create( path+"/c1", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT );
    	
    	zk.delete( path+"/c1", -1 );
    	
    	zk.delete( path, -1 );
    	
        Thread.sleep( Integer.MAX_VALUE );
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (KeeperState.SyncConnected == event.getState()) {
                if (EventType.None == event.getType() && null == event.getPath()) {
                    connectedSemaphore.countDown();
                } else if (EventType.NodeCreated == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ")Created");
                    zk.exists( event.getPath(), true );
                } else if (EventType.NodeDeleted == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ")Deleted");
                    zk.exists( event.getPath(), true );
                } else if (EventType.NodeDataChanged == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ")DataChanged");
                    zk.exists( event.getPath(), true );
                }
            }
        } catch (Exception e) {}
    }
}