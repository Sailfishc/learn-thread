package com.sailfish.lambda;

/**
 * @author sailfish
 * @create 2017-05-18-上午10:32
 */
public interface IAnimal {

    default void breath(){
        System.out.println("breath");
    }
}
