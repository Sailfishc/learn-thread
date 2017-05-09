package com.sailfish.ch7.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author sailfish
 * @create 2017-05-09-下午8:27
 */
public class PCDataFactory implements EventFactory<PCData> {

    public PCData newInstance() {
        return new PCData();
    }
}
