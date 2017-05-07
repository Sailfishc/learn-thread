package com.sailfish.ch5;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 判断是否有死锁发生
 * @author sailfish
 * @create 2017-05-07-上午8:43
 */
public class DeadLockChecker {

    private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
    final static Runnable deadLockCheck = new Runnable() {
        @Override
        public void run() {
            while (true) {
                long[] deadlockedThreads = mbean.findDeadlockedThreads();
                if (deadlockedThreads != null) {
                    ThreadInfo[] threadInfo = mbean.getThreadInfo(deadlockedThreads);
                    for (Thread thread : Thread.getAllStackTraces().keySet()) {
                        for(int i = 0; i < threadInfo.length; i++) {
                            if (thread.getId() == threadInfo[i].getThreadId()) {
                                thread.interrupt();
                            }
                        }
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        Thread t = new Thread(deadLockCheck);
        //设置为守护线程，在推出后不影响虚拟机退出（业务退出）
        t.setDaemon(true);
        t.start();
    }
}
