package com.sailfish.ch5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁申请等待限时
 * @author sailfish
 * @create 2017-05-07-下午2:39
 */
public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(7, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
                System.out.println("get lock success");
            } else {
                System.out.println("get lock fail");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock demo = new TimeLock();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
    }
}
/*
lock.tryLock();
lock.tryLock(time, TimeUnit);
 */