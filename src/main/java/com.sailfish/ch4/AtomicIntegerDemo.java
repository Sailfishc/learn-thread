package com.sailfish.ch4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 针对《Java高并发程序设计》无锁章节的测试AtomicInteger和锁的性能对比
 * 对比结果：
 * 多线程：
 *      1000000
        sync-time:73
        1000000
         atom-time:91
 *  单线程：
 *      100000000
        sync-time:9820
        100000000
        atom-time:4173
 * @author sailfish
 * @create 2017-05-04-下午1:04
 */
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();
    public static Object o = new Object();

    public static class addThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 100000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static int a = 0;
    public static class addThread2 implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 100000; k++) {
                synchronized (o) {
                    a++;
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start2 = System.currentTimeMillis();
        Thread[] t2 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            t2[i] = new Thread(new addThread2());
        }
        for (int i = 0; i < 10; i++) {
            t2[i].start();
        }
        for (int i = 0; i < 10; i++) {
            t2[i].join();
        }
//        new addThread2().run();
        System.out.println(a);
        System.out.println("sync-time:"+(System.currentTimeMillis() - start2));


        long start = System.currentTimeMillis();
        Thread[] t = new Thread[10];
        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(new addThread());
        }
        for (int i = 0; i < 10; i++) {
            t[i].start();
        }
        for (int i = 0; i < 10; i++) {
            t[i].join();
        }
//        new addThread().run();
        System.out.println(i);
        System.out.println("atom-time:"+(System.currentTimeMillis() - start));


    }
}
