package com.sailfish.ch7.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sailfish
 * @create 2017-05-09-下午6:01
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = false;
    private BlockingQueue<PcData> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PcData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PcData data = null;
        Random r = new Random();

        System.out.println("start producer id" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PcData(count.incrementAndGet()); //构造任务数据
                System.out.println(data + " is put into queue");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {  //提交数据到缓冲区
                    System.out.println("fail to put data:" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
