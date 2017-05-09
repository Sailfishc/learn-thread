package com.sailfish.ch7.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author sailfish
 * @create 2017-05-09-下午8:24
 */
public class Consumer implements WorkHandler<PCData> {

    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: --" + event.get() * event.get() + "--");

    }

}
