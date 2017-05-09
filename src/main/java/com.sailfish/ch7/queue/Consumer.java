package com.sailfish.ch7.queue;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author sailfish
 * @create 2017-05-09-下午7:14
 */
public class Consumer implements Runnable {
    private BlockingQueue<PcData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PcData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id=" + Thread.currentThread().getId());
        Random r = new Random();

        try {
            while (true) {
                PcData data = queue.take();
                if (null == data) {
                    int re = data.getData() * data.getData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
