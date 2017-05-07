package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-25-下午8:00
 */
public class InterruptDemo extends Thread{

    @Override
    public void run() {
        while (true) {
            //判断是否被中断
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrupt...");
                break;
            }
            //谦让线程
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        InterruptDemo t = new InterruptDemo();
        t.start();
        t.interrupt();
    }
}
