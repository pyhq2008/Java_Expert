package com.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by baoxue on 16/9/7.
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
