package com.baoxue.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2016/5/23.
 */
public class SocketChannelApp {

    public static void main(String[] args) throws IOException {
        testWrite();
    }


    public static void testWrite() throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(2134));
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }
    }

    /**
     * �������� SocketChannel Ϊ������ģʽ��non-blocking mode��.����֮��
     * �Ϳ������첽ģʽ�µ���connect(), read() ��write()�ˡ�
     */
    public void testNonBlock() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(30));
        while(!socketChannel.finishConnect() ){
            //wait, or do something else...
        }

    }
}
