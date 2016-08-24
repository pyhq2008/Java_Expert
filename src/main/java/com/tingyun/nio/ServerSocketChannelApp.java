package com.tingyun.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ServerSocketChannelApp {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        while(true){
            /** 阻塞模式下
             * 通过 ServerSocketChannel.accept() 方法监听新进来的连接。
             * 当 accept()方法返回的时候,它返回一个包含新进来的连接的 SocketChannel。
             * 因此, accept()方法会一直阻塞到有新连接到达
             */
            SocketChannel socketChannel = serverSocketChannel.accept();
            //do something with socketChannel...
        }
    }


    /**
     * ServerSocketChannel可以设置成非阻塞模式。
     * 在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。
     * 因此，需要检查返回的SocketChannel是否是null.
     * @throws IOException
     */
    public void  testNonblock() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            /**
             * 在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。
             * 因此，需要检查返回的SocketChannel是否是null.
             */
            if(socketChannel != null){
                //do something with socketChannel...
            }
        }

    }
}
