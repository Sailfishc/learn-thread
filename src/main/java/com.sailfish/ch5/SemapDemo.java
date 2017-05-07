package com.sailfish.ch5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * @author sailfish
 * @create 2017-05-07-上午9:25
 */
public class SemapDemo implements Runnable{

    final Semaphore semp = new Semaphore(5);  //指定5个许可

    @Override
    public void run() {
        try {
            semp.acquire();
//            semp.acquire(2); 如果这定了2个许可，那么也就只有两个线程可以拿得到许可，就会出现每次出现2个线程进入

            //模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);//指定20个线程
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i< 20; i++) {
            exec.submit(demo);//参数为task，其实就是线程
        }
        exec.shutdown();
    }
}
