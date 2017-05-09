package com.sailfish.ch7.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author sailfish
 * @create 2017-05-09-下午7:18
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PcData> queue = new LinkedBlockingDeque<>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);

        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        Thread.sleep(10 * 1000);
        //停止生产者
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(3000);
        service.shutdown();
    }
}
