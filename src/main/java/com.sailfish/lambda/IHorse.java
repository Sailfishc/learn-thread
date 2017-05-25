package com.sailfish.lambda;

/**
 * @author sailfish
 * @create 2017-05-18-上午10:31
 */
public interface IHorse {

    void eat();
    default void run(){
        System.out.println("horse run");
    }
}
