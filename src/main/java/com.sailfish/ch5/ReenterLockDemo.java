package com.sailfish.ch5;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 * @author sailfish
 * @create 2017-05-07-下午2:13
 */
public class ReenterLockDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int j = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            lock.lock();   //加锁
            try {
                j++;
            }finally {
                lock.unlock();   //解锁
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockDemo demo = new ReenterLockDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(j);
    }
}
