package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-27-上午7:55
 */
public class JoinMain {

    public volatile  static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
