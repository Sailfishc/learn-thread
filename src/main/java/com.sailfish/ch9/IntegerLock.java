package com.sailfish.ch9;

/**
 * @author sailfish
 * @create 2017-05-11-上午6:38
 */
public class IntegerLock {

    static Integer i = 0;
    public static class AddThread extends Thread{

        @Override
        public void run() {
            for (int k = 0; k<10000; k++) {
                synchronized (i) {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        AddThread t2 = new AddThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i); //13170,总是小于20000
    }
}
