package com.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by baoxue on 16/9/7.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
