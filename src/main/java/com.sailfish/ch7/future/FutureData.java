package com.sailfish.ch7.future;

/**
 * @author sailfish
 * @create 2017-05-09-下午9:09
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setResult(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
