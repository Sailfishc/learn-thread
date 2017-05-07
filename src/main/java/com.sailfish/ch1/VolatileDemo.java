package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-27-上午8:05
 */
public class VolatileDemo {

    public static  Object u = new Object();
    static volatile int i = 0;
    public static class PlusTask implements Runnable{

        @Override
        public void run() {
            for (int k = 0; k<10000; k++) {
                synchronized (u){
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i<10 ;i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }

        for (int i = 0; i < 10 ; i++) {
            threads[i].join();
        }
        System.out.println(i);
    }
}
/*
    78143:无法保证一些符合操作
    100000:加了synchronized
 */