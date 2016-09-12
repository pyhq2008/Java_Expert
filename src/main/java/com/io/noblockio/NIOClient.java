package com.io.noblockio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * Created by baoxue on 16/9/12.
 */
public class NIOClient {

    private Selector selector;

    public void init(String ip, int port) throws IOException{

        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);

        this.selector = SelectorProvider.provider().openSelector();
        //由于channel是非阻塞的,所以connect返回时,链接并不一定建立
        channel.connect(new InetSocketAddress(ip,port));

        channel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void  working() throws IOException{

        while(true){
            if(!selector.isOpen()){
                break;
            }
            selector.select();

            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();

            while(ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();

                if(key.isConnectable()){
                    connect(key);
                }else if(key.isReadable()){
                    read(key);
                }
            }

        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();
        //创建读缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("client recevice inifo :" + msg);

        channel.close();
        key.selector().close();
    }

    private void connect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();
        //如果正在链接则完成连接
        if(channel.isConnectionPending()){
            channel.finishConnect();
        }
        channel.configureBlocking(false);
        channel.write(ByteBuffer.wrap(new String("Hello nio Server!\r\n").getBytes()));
        channel.register(selector,SelectionKey.OP_READ);

    }

    public static  void main(String[] args){
        NIOClient nioClient = new NIOClient();
        try {
//            System.out.println(new String(InetAddress.getLocalHost().getAddress()));
            nioClient.init("127.0.0.1",8000);
            nioClient.working();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
