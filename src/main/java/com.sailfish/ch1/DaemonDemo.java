package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-27-上午8:28
 */
public class DaemonDemo {

    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println("i am live");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
/*
工作线程结束，程序就退出了，所以只会打印两次
 */