package com.io.noblockio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

/**
 * Created by baoxue on 16/9/11.
 */
public class HandleMsg implements Runnable {

    SelectionKey sk;
    ByteBuffer bb;

    public HandleMsg(SelectionKey sk, ByteBuffer bb) {
        this.sk = sk;
        this.bb = bb;
    }

    @Override
    public void run() {
        EchoClient echoClient = (EchoClient)sk.attachment();
        echoClient.enqueue(bb);
        sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }
}
