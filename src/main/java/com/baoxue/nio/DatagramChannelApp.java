package com.baoxue.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *
 * DatagramChannel是一个能收发UDP包的通道。
 * 因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。
 * 它发送和接收的是数据包。
 */
public class DatagramChannelApp {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        //通过receive()方法从DatagramChannel接收数据
        //receive()方法会将接收到的数据包内容复制到指定的Buffer.
        // 如果Buffer容不下收到的数据，多出的数据将被丢弃
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);

        //通过send()方法从DatagramChannel发送数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        buf2.clear();
        buf2.put(newData.getBytes());
        buf2.flip();
        //这个例子发送一串字符到”jenkov.com”服务器的UDP端口80。
        // 因为服务端并没有监控这个端口，所以什么也不会发生。
        // 也不会通知你发出的数据包是否已收到，因为UDP在数据传送方面没有任何保证。
        int bytesSent = channel.send(buf2, new InetSocketAddress("jenkov.com", 80));




    }
}
