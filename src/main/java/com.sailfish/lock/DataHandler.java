package com.sailfish.lock;

/**
 * @author sailfish
 * @create 2019-01-31-4:53 PM
 */
public class DataHandler {


    public static synchronized void read() {
        System.out.println(Thread.currentThread().getName() + ", 进入方法...");

    }
}
