package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-25-下午8:17
 */
public class BadSuspend {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(2000);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
/**
 * 备注：t1已经结束，t2还在执行（永久挂起）
 * 原因：
 * t1之前调用了suspend方法
 * 流程：
 * 1、t1.start
 * 2、t1.suspend(挂起)
 * 3、t1.sleep
 * 4、t2.start
 * 5、t1.resume
 * 6、t2.resume(t2还没有被suspend(挂起)，被堵塞了)
 *
   1、 jps
    26781
    30448 RemoteMavenServer
    30668 Jps
    30666 Launcher
    30667 AppMain

    2、 jstack 30667
 * "t2" prio=5 tid=0x00007fa16b800800 nid=0x5703 runnable [0x0000700009dbb000]
    java.lang.Thread.State: RUNNABLE
    at java.lang.Thread.suspend0(Native Method)
    at java.lang.Thread.suspend(Thread.java:1068)
    at com.sailfish.ch1.BadSuspend$ChangeObjectThread.run(BadSuspend.java:22)
    - locked <0x00000007aab23e00> (a java.lang.Object)
 */