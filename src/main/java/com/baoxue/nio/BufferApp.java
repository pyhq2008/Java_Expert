package com.baoxue.nio;

/**
 * Java NIO��ʼ֧��scatter/gather��scatter/gather����������Channel�ж�ȡ ���� д�뵽Channel�Ĳ�����
 ��ɢ��scatter����Channel�ж�ȡ ��ָ�ڶ�����ʱ����ȡ������д����buffer�С�
                              ��ˣ�Channel����Channel�ж�ȡ�����ݡ���ɢ��scatter���������Buffer�С�
 �ۼ���gather��д��Channel��ָ��д����ʱ�����buffer������д��ͬһ��Channel��
                              ��ˣ�Channel �����Buffer�е����ݡ��ۼ���gather�������͵�Channel��

 scatter / gather����������Ҫ����������ݷֿ�����ĳ��ϣ�
 ���紫��һ������Ϣͷ����Ϣ����ɵ���Ϣ������ܻὫ��Ϣ�����Ϣͷ��ɢ����ͬ��buffer�У���������Է���Ĵ�����Ϣͷ����Ϣ�塣
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
