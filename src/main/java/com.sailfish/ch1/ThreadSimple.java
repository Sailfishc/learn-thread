package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-25-下午7:27
 */
public class ThreadSimple extends Thread{


    @Override
    public void run() {
        System.out.println("thread1");
    }

    public static void main(String[] args) {
        ThreadSimple t1 = new ThreadSimple();
//        t1.run();
        t1.start();
    }
}
