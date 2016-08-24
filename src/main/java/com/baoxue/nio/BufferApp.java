package com.baoxue.nio;

/**
 * Java NIO开始支持scatter/gather，scatter/gather用于描述从Channel中读取 或者 写入到Channel的操作。
 分散（scatter）从Channel中读取 是指在读操作时将读取的数据写入多个buffer中。
                              因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，
                              因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。

 scatter / gather经常用于需要将传输的数据分开处理的场合，
 例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。
 */
public class BufferApp {

    public static void main(String[] args){
//        ByteBuffer header = ByteBuffer.allocate(128);
//        ByteBuffer body   = ByteBuffer.allocate(1024);
//
//        ByteBuffer[] bufferArray = { header, body };
//
//        channel.read(bufferArray);
//-------------------------------------------------------------------------
//        ByteBuffer header = ByteBuffer.allocate(128);
//        ByteBuffer body   = ByteBuffer.allocate(1024);
//
////      write data into buffers
//
//        ByteBuffer[] bufferArray = { header, body };
//
//        channel.write(bufferArray);
    }
}
