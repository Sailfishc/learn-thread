package com.sailfish.ch7.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author sailfish
 * @create 2017-05-09-下午8:28
 */
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try{
            PCData event = ringBuffer.get(sequence);
            event.set(bb.getLong(0));
        }
        finally {
            ringBuffer.publish(sequence);
        }
    }
}
