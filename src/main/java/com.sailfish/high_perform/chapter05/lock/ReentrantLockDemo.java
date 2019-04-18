package com.sailfish.high_perform.chapter05.lock;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sailfish
 * @create 2019-04-18-10:44
 */
public class ReentrantLockDemo {

    private static Lock lock = new ReentrantLock();
    static final Condition empty = lock.newCondition();
    static final Condition full = lock.newCondition();
    private static int queueSize = 10;
    private static Queue<Integer> queue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) {
        new Thread(new Consumer()).start();
        new Thread(new Provider()).start();
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    while (queue.size() == 0) {
                        empty.await();
                    }
                    System.out.println("poll an element with queue. value = " + queue.poll());
                    full.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Provider implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    while (queue.size() == queueSize) {
                        full.await();
                    }
                    final int ele = new Random().nextInt(1000);
                    System.out.println("offer an element with queue. value = " + queue.offer(ele));
                    empty.signal();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
