package com.sailfish.ch9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 没有使用ThreadLocal的案例
 * @author sailfish
 * @create 2017-05-11-上午6:43
 */
public class ThreadLocalDemo {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            Date t = null;
            try {
                t = sdf.parse("2015-03-29 19:29:" + i % 60);
                System.out.println(i + ":" + t);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i = 0; i<1000; i++) {
            service.execute(new ParseDate(i));
        }
    }
}
/*
Exception in thread "pool-1-thread-250" Exception in thread "pool-1-thread-249" java.lang.NumberFormatException: For input string: ""
Exception in thread "pool-1-thread-261" Exception in thread "pool-1-thread-267" Exception in thread "pool-1-thread-237" Exception in thread "pool-1-thread-252" Exception in thread "pool-1-thread-240" java.lang.NumberFormatException: For input string: ""
Exception in thread "pool-1-thread-229" Exception in thread "pool-1-thread-238" Exception in thread "pool-1-thread-269" Exception in thread "pool-1-thread-245" Exception in thread "pool-1-thread-236" java.lang.NumberFormatException: For input string: ".1919"
Exception in thread "pool-1-thread-221" java.lang.NumberFormatException: For input string: "20152015E"
 */