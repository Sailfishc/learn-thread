package com.sailfish.ch9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sailfish
 * @create 2017-05-11-上午6:48
 */
public class ThreadLocalDemo2 {

    static ThreadLocal<SimpleDateFormat> ts = new ThreadLocal<>();

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            if (ts.get() == null) {
                ts.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            try {
                Date date = ts.get().parse("2015-03-29 19:29:" + i % 60);
                System.out.println(i + "=" + date);
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
