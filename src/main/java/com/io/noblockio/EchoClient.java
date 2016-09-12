package com.io.noblockio;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * Created by baoxue on 16/9/11.
 */
public class EchoClient {

    private LinkedList<ByteBuffer> outq;

    EchoClient(){
        outq = new LinkedList<ByteBuffer>();
    }

    public LinkedList<ByteBuffer> getOutputQueue(){
        return this.outq;
    }

    public void enqueue(ByteBuffer bb){
        outq.addFirst(bb);
    }
}
