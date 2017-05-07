package com.sailfish.ch5;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * @author sailfish
 * @create 2017-05-07-下午3:08
 */
public class FairLock implements Runnable{
    public static ReentrantLock fair = new ReentrantLock(true);//公平锁

    @Override
    public void run() {
        while (true) {
            try {
                fair.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            }finally {
                fair.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock lock = new FairLock();
        Thread t1 = new Thread(lock, "Thread_t1");
        Thread t2 = new Thread(lock, "Thread_t2");
        t1.start();
        t2.start();
    }
}
/*
Thread_t1 获得锁
Thread_t2 获得锁
Thread_t1 获得锁
Thread_t2 获得锁
Thread_t1 获得锁
Thread_t2 获得锁
Thread_t1 获得锁
Thread_t2 获得锁
Thread_t1 获得锁
Thread_t2 获得锁
Thread_t1 获得锁
Thread_t2 获得锁
Thread_t1 获得锁
Thread_t2 获得锁
 */