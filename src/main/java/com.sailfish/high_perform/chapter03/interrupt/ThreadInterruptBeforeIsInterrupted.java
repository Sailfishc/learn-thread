package com.sailfish.high_perform.chapter03.interrupt;


import java.util.concurrent.TimeUnit;

/**
 * @author sailfish
 * @create 2019-01-27-9:53 AM
 */
public class ThreadInterruptBeforeIsInterrupted {

    public static void main(String[] args) {
        //1 判断当前线程是否被中断
        System.out.println("Main thread is interrupted? " + Thread.interrupted());
//2中 断当前线程
        Thread.currentThread().interrupt();
//3判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
// 5捕获中断信号
            System.out.println("I will be interrupted still.");
        }
    }
}
