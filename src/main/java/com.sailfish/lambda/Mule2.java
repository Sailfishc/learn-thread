package com.sailfish.lambda;

/**
 * @author sailfish
 * @create 2017-05-18-上午10:37
 */
public class Mule2 implements IHorse, IAnimal, IDonkey{

    @Override
    public void eat() {
        System.out.println("mule2 eat");
    }

    @Override
    public void run() {
        IDonkey.super.run();
    }

    public static void main(String[] args) {
        Mule2 m = new Mule2();
        m.eat();
        m.run();
    }
}
