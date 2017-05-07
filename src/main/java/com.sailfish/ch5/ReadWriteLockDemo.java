package com.sailfish.ch5;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author sailfish
 * @create 2017-05-07-上午10:03
 */
public class ReadWriteLockDemo {

    private static final Lock lock = new ReentrantLock();//重入锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            //模拟读操作
            lock.lock();
            //读操作的耗时越多，写操作的优势越明显
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnalbe = new Runnable() {
            @Override
            public void run() {
                try {
//                    demo.handleRead(readLock);
                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Runnable writeRunnable  = new Runnable() {
            @Override
            public void run() {
                try {
//                    demo.handleWrite(writeLock, new Random().nextInt());
                    demo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

//        long start = System.currentTimeMillis();
        for(int i = 0; i < 18; i++) {
            new Thread(readRunnalbe).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
//        System.out.println("共用时:"+(System.currentTimeMillis() - start));
    }
}
