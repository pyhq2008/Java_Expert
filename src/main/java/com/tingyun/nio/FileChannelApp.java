package com.tingyun.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/5/23.
 */
public class FileChannelApp {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("E:\\nio-test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            //��ת�˻�����
            //���ȶ�ȡ���ݵ�Buffer��Ȼ��תBuffer,�����ٴ�Buffer�ж�ȡ���ݡ�
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }



    public void testWrite() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("E:\\nio-test.txt", "rw");
        FileChannel channel = aFile.getChannel();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while(buf.hasRemaining()) {
            channel.write(buf);
        }
        channel.close();
    }
}
