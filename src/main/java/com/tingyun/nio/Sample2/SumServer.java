package com.tingyun.nio.Sample2;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
/**
 * SumServer.java
 * 
 * 
 * Created: Thu Nov 06 11:41:52 2003
 * 
 * @author starchu1981
 * @version 1.0
 */
public class SumServer {
	private ByteBuffer _buffer = ByteBuffer.allocate(8);
	private IntBuffer _intBuffer = _buffer.asIntBuffer();
	private SocketChannel _clientChannel = null;
	private ServerSocketChannel _serverChannel = null;
	public void start() {
		try {
			openChannel();
			waitForConnection();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	private void openChannel() throws IOException {
		_serverChannel = ServerSocketChannel.open();
		_serverChannel.socket().bind(new InetSocketAddress(10001));
		_serverChannel.configureBlocking(false);// ����Ϊ��������ʽ
		System.out.println("Server channel is open ......");
	}
	/*
	 * private void waitForConnection() throws IOException { while (true) {
	 * _clientChannel = _serverChannel.accept(); if (_clientChannel != null) {
	 * System.out.println("�µ����Ӽ���"); processRequest(); _clientChannel.close(); } } }
	 */
	private void waitForConnection() throws IOException {
		Selector acceptSelector = SelectorProvider.provider().openSelector();
		/*
		 * �ڷ������׽�����ע��selector������Ϊ����accept������֪ͨ��
		 * ��͸���Selector���׽�����Ҫ��accept��������ʱ������ready���ϣ���ˣ������Ԫ������I/O������
		 */
		SelectionKey acceptKey = _serverChannel.register(acceptSelector,
				SelectionKey.OP_ACCEPT);
		int keysAdded = 0;
		/* select�������κ�����ע���˵Ĳ�������ʱ���� */
		while ((keysAdded = acceptSelector.select()) > 0) {
			// ĳ�ͻ��Ѿ�׼���ÿ��Խ���I/O�����ˣ���ȡ��ready������
			Set readyKeys = acceptSelector.selectedKeys();
			Iterator i = readyKeys.iterator();
			// ����ready�����ϣ�������ӷ�����
			while (i.hasNext()) {
				SelectionKey sk = (SelectionKey) i.next();
				i.remove();
				if (sk.isAcceptable()) {
					ServerSocketChannel nextReady = (ServerSocketChannel) sk.channel();
					// ���ܼӷ����󲢴�����
					_clientChannel = nextReady.accept();
				//	Socket _clientSocket = _clientChannel.socket();
					processRequest();
				//	_clientSocket.close();
				}
			}
		}
	}
	private void processRequest() throws IOException {
		_buffer.clear();
		_clientChannel.read(_buffer);
		int result = _intBuffer.get(0) + _intBuffer.get(1);
		_buffer.flip();
		_buffer.clear();
		_intBuffer.put(0, result);
		_clientChannel.write(_buffer);
	}
	public static void main(String[] args) {
		new SumServer().start();
	}
} 