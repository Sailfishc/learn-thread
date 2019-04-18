package com.sailfish.lock;

/**
 * @author sailfish
 * @create 2019-01-31-4:57 PM
 */
public class Client {


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new ReadThread().start();
        }

    }
}
