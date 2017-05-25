package com.sailfish.ch6;

/**
 * @author sailfish
 * @create 2017-05-16-上午10:43
 */
public class DeadLock extends Thread {

    protected Object tool;
    static Object fork1 = new Object();  //模拟哲学家1
    static Object fork2 = new Object();  //模拟哲学家2

    public DeadLock(Object tool) {
        this.tool = tool;

        if (tool == fork1) {
            this.setName("哲学家A");
        }
        if (tool == fork2) {
            this.setName("哲学家B");
        }
    }

    @Override
    public void run() {
        if (tool == fork1) {
            synchronized (fork1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork2) {
                    System.out.println("哲学家A开始吃饭了!");
                }
            }
        } else if (tool == fork2) {
            synchronized (fork2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork1) {
                    System.out.println("哲学家B开始吃饭了!");
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DeadLock A = new DeadLock(fork1);
        DeadLock B = new DeadLock(fork2);
        A.start();
        B.start();
        Thread.sleep(1000);
    }
}
