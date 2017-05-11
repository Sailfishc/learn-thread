package com.sailfish.ch9;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试通过多线程产生随机数所花费的时间
 * @author sailfish
 * @create 2017-05-11-上午7:30
 */
public class TestRandomTime {

    public static final int GEN_COUNT = 10000000;
    public static final int THREAD_COUNT = 4;
    static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random rdn = new Random(123);

    public static ThreadLocal<Random> trdn = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RdnTask implements Callable<Long> {

        private int mode = 0;

        public RdnTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom(){
            if (mode == 0) {
                return rdn;
            } else if (mode == 1) {
                return trdn.get();
            } else {
                return null;
            }
        }
        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for (long l = 0; l<GEN_COUNT; l++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " spend " + (e - b) + "ms");
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long> [] future = new Future[THREAD_COUNT];
        for (int i = 0; i<THREAD_COUNT; i++) {
            future[i] = exe.submit(new RdnTask(0));
        }

        long totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += future[i].get();
        }
        System.out.println("多线程访问同一个random实例：" + totalTime + "ms");

        for (int i = 0; i<THREAD_COUNT; i++) {
            future[i] = exe.submit(new RdnTask(1));
        }

        totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += future[i].get();
        }
        System.out.println("使用ThreadLocal包装random实例：" + totalTime + "ms");
        exe.shutdown();
    }
}
