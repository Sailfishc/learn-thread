package com.sailfish.ch9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 跟踪gc
 *
 * @author sailfish
 * @create 2017-05-11-上午7:03
 */
public class ThreadLocalDemo_GC {

    static volatile ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " is gc");
        }
    };
    static volatile CountDownLatch cd = new CountDownLatch(10000);

    public static class ParseDate implements Runnable {

        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + ": is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");

                }

                Date date = tl.get().parse("2015-03-29 19:29:" + i % 60);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                cd.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("missing complate");
        tl = null;
        System.gc();
        System.out.println("first gc complete!!!");

        tl = new ThreadLocal<>();
        cd = new CountDownLatch(10000);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("second gc complete!!!");
    }
}
