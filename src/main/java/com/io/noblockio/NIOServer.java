package com.io.noblockio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by baoxue on 16/9/11.
 */
public class NIOServer {

    private Selector selector;

    private ExecutorService tp = Executors.newCachedThreadPool();

    public static Map<Socket,Long> time_stat = new HashMap<Socket,Long>(10240);

    private void  startServer() throws  Exception{

        selector = SelectorProvider.provider().openSelector();

        ServerSocketChannel ssc = ServerSocketChannel.open();

        //配置为非阻塞模式,也可配置为阻塞模式
        ssc.configureBlocking(false);

//        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(),8000);
        InetSocketAddress isa = new InetSocketAddress(8000);

        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector,SelectionKey.OP_ACCEPT);

        for(;;){
            //当注册的事件到达时，方法返回；否则，该方法会一直阻塞
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e = 0;
            while (i.hasNext()){
                SelectionKey sk = (SelectionKey)i.next();
                //删除已选的key，以防重复处理
                i.remove();

                if(sk.isAcceptable()){
                    doAccept(sk);
                }else if (sk.isValid() && sk.isReadable()){
                    if(!time_stat.containsKey(((SocketChannel)sk.channel()).socket())){
                        time_stat.put(((SocketChannel)sk.channel()).socket(),System.currentTimeMillis());
                        doRead(sk);
                    }
                }else if (sk.isValid() && sk.isWritable()){
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("this nio speed :"+ (e - b) + "ms");
                }

            }



        }


    }




    private void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel)sk.channel();
        SocketChannel clientChannel;

        try{
            //获得和客户端连接的通道
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读权限
            SelectionKey clientKey = clientChannel.register(selector,SelectionKey.OP_READ);

            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accept connection from " + clientAddress.getHostAddress() + ".");


        }catch (Exception e){
            System.out.println("Failed to accept new client .");
            e.printStackTrace();
        }
    }

    private void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel)sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try{
            len = channel.read(bb);
            if(len < 0){
                disconnect(sk);
                return;
            }
        }catch (Exception e){
            System.out.println("Failed to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }

        bb.flip();
        tp.execute(new HandleMsg(sk,bb));
    }



    private void doWrite(SelectionKey sk) {

        SocketChannel channel = (SocketChannel) sk.channel();

        EchoClient echoClient = (EchoClient) sk.attachment();

        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();

        try{
            int len = channel.write(bb);
            if(len == -1){
                disconnect(sk);
                return;
            }

            if(bb.remaining() == 0){
                //the buffer was completely written ,remove it.
                outq.removeLast();
            }
        }catch (Exception e){
            System.out.println("Fail to write to client.");
            e.printStackTrace();
            disconnect(sk);
        }

        if(outq.size() == 0){
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    private void disconnect(SelectionKey sk) {
        try {
            sk.channel().close();
            //不能关掉sever端的selector,关掉selector,server无法再提供服务。
//            sk.selector().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args){
        NIOServer nioServer = new NIOServer();
        try {
            nioServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
