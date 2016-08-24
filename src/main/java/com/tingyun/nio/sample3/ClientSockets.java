package com.tingyun.nio.sample3;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2016/5/31.
 */
public class ClientSockets {

    public static int PORT_NUMBER = 1234;


    public static void main(String[] argv) throws Exception {
        InetSocketAddress socketAddress = new InetSocketAddress(PORT_NUMBER);
        SocketChannel socketChannel = SocketChannel.open(socketAddress);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.putChar('a');
        buffer.putChar('b');

        socketChannel.write(buffer);
        System.out.println("Send add request : ab ");
        buffer.clear();

        socketChannel.read(buffer);

        System.out.println(buffer.get());

        socketChannel.close();

    }
}
