package com.sailfish.ch5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sailfish
 * @create 2017-05-07-上午10:14
 */
public class CountDownLatchDemo implements Runnable{

    static final CountDownLatch end = new CountDownLatch(10);//初始化为10
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {

        //模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("complate..");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i ++) {
            exec.submit(demo);
        }

        end.await();
        System.out.println("fire");
        exec.shutdown();
    }
}
