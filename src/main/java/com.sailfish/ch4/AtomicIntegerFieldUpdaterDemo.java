package com.sailfish.ch4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author sailfish
 * @create 2017-05-03-下午10:23
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static class Candidate{
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    //这个用来检查AtomicIntegerFieldUpdater的正确性
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            t[i] = new Thread(){
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            t[i].join();
        }

        System.out.println("score=" + stu.score);
        System.out.println("allScore=" + allScore);
    }
}
