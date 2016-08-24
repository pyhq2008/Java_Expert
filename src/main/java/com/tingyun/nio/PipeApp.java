package com.tingyun.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by Administrator on 2016/5/23.
 */
public class PipeApp {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        //要向管道写数据，需要访问sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

       // 从读取管道的数据，需要访问source通道
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(buf2);

    }
}
