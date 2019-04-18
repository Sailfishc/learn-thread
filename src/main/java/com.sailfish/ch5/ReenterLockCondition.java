package com.sailfish.ch5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sailfish
 * @create 2017-05-07-下午3:14
 */
public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {

        try {
            System.out.println("Thread coming ...");
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition r1 = new ReenterLockCondition();
        Thread t1 = new Thread(r1);
        t1.start();
        Thread.sleep(2000);
        //通知线程t1继续执行
        lock.lock();
        System.out.println("singal thread ... ");
        condition.signal();
        lock.unlock();
    }
}
