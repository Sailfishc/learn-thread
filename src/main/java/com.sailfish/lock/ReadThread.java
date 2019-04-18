package com.sailfish.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author sailfish
 * @create 2019-01-31-4:55 PM
 */
public class ReadThread extends Thread {


    @Override
    public void run() {
        try {
            DataHandler.read();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
