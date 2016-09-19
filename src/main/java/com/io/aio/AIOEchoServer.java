package com.io.aio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步非阻塞的Server
 * Created by baoxue on 16/9/20.
 */
public class AIOEchoServer {

    public final static int PORT = 8000;

    private AsynchronousServerSocketChannel server;

    public AIOEchoServer() throws Exception{
        server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
    }

    public void start(){
        System.out.println("Server listen on " + PORT);
        //注册事件和事件完成后的处理器
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println(Thread.currentThread().getName());
                Future<Integer> writeResult = null ;
                try{
                    buffer.clear();
                    result.read(buffer).get(100, TimeUnit.SECONDS);
                    buffer.flip();
                    writeResult = result.write(buffer);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        server.accept(null, this);
                        writeResult.get();
                        result.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed: " + exc);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new AIOEchoServer().start();
        //主线程可以继续自己的行为
        while (true){
            Thread.sleep(1000);
        }
    }
}
