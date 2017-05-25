package com.sailfish.lambda;

/**
 * @author sailfish
 * @create 2017-05-18-上午10:36
 */
public interface IDonkey {

    void eat();
    default void run(){
        System.out.println("donkey run");
    }
}
