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
            /** ����ģʽ��
             * ͨ�� ServerSocketChannel.accept() ���������½��������ӡ�
             * �� accept()�������ص�ʱ��,������һ�������½��������ӵ� SocketChannel��
             * ���, accept()������һֱ�������������ӵ���
             */
            SocketChannel socketChannel = serverSocketChannel.accept();
            //do something with socketChannel...
        }
    }


    /**
     * ServerSocketChannel�������óɷ�����ģʽ��
     * �ڷ�����ģʽ�£�accept() ���������̷��أ������û���½���������,���صĽ���null��
     * ��ˣ���Ҫ��鷵�ص�SocketChannel�Ƿ���null.
     * @throws IOException
     */
    public void  testNonblock() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            /**
             * �ڷ�����ģʽ�£�accept() ���������̷��أ������û���½���������,���صĽ���null��
             * ��ˣ���Ҫ��鷵�ص�SocketChannel�Ƿ���null.
             */
            if(socketChannel != null){
                //do something with socketChannel...
            }
        }

    }
}
