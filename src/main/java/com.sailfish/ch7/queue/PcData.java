package com.sailfish.ch7.queue;

/**
 *
 * 任务相关的数据
 * @author sailfish
 * @create 2017-05-09-下午5:58
 */
public class PcData {

    private final int intData;//数据

    public PcData(int intData) {
        this.intData = intData;
    }

    public PcData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getData(){
        return intData;
    }

    @Override
    public String toString() {
        return "data" + intData;
    }
}
