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
        //Ҫ��ܵ�д���ݣ���Ҫ����sinkͨ��
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

       // �Ӷ�ȡ�ܵ������ݣ���Ҫ����sourceͨ��
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(buf2);

    }
}
