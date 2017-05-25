package com.sailfish.lambda;

/**
 * @author sailfish
 * @create 2017-05-18-上午10:34
 */
public class Mule implements IHorse, IAnimal {

    @Override
    public void eat() {
        System.out.println("mule eat");
    }

    public static void main(String[] args) {
        Mule m = new Mule();
        m.run();
        m.eat();
    }
}
