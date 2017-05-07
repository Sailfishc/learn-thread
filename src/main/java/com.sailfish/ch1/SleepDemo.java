package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-25-下午8:07
 */
public class SleepDemo extends Thread{

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrupted...");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("interrupted when sleep///");
                //设置中断标志位，抛出异常后清除中断标志
                Thread.currentThread().interrupt();
            }
            Thread.yield();
        }
    }
}
