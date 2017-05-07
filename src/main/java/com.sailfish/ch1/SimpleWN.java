package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-26-上午8:36
 */
public class SimpleWN {
    final static Object OBJECT = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (OBJECT) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object!");
                    OBJECT.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (OBJECT) {
                System.out.println(System.currentTimeMillis() + ":T2 start!, notify one thread");

                OBJECT.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
/*
1493167317902:T1 start!
1493167317903:T1 wait for object!
1493167317903:T2 start!, notify one thread
1493167317903:T2 end!
1493167319907:T1 end!
 */