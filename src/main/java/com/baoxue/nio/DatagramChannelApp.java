package com.baoxue.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *
 * DatagramChannel��һ�����շ�UDP����ͨ����
 * ��ΪUDP�������ӵ�����Э�飬���Բ���������ͨ��������ȡ��д�롣
 * �����ͺͽ��յ������ݰ���
 */
public class DatagramChannelApp {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        //ͨ��receive()������DatagramChannel��������
        //receive()�����Ὣ���յ������ݰ����ݸ��Ƶ�ָ����Buffer.
        // ���Buffer�ݲ����յ������ݣ���������ݽ�������
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);

        //ͨ��send()������DatagramChannel��������
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        buf2.clear();
        buf2.put(newData.getBytes());
        buf2.flip();
        //������ӷ���һ���ַ�����jenkov.com����������UDP�˿�80��
        // ��Ϊ����˲�û�м������˿ڣ�����ʲôҲ���ᷢ����
        // Ҳ����֪ͨ�㷢�������ݰ��Ƿ����յ�����ΪUDP�����ݴ��ͷ���û���κα�֤��
        int bytesSent = channel.send(buf2, new InetSocketAddress("jenkov.com", 80));




    }
}
