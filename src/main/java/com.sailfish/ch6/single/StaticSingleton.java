package com.sailfish.ch6.single;

/**
 * 利用虚拟机的类初始化实现单例
 * @author sailfish
 * @create 2017-05-16-下午2:49
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("StaticSingleton is created!");
    }

    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }

}
