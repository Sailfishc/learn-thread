package com.sailfish.ch7;

/**
 * 静态单例模式(效率高）
 * @author sailfish
 * @create 2017-05-09-下午1:24
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("going to singleton");
    }

    private static class SingleHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingleHolder.instance;
    }
}
