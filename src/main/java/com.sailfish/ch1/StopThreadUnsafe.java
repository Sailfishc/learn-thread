package com.sailfish.ch1;

/**
 * @author sailfish
 * @create 2017-04-26-上午8:12
 */
public class StopThreadUnsafe {

    public static User u  = new User();
    public static class User{
        private int id;
        private String name;

        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static class ChangeObjectThreadSafe extends Thread{

        volatile boolean stopme = false;

        public void stopMe(){
            stopme = true;
        }

        @Override
        public void run() {
            while (true) {
                if (stopme){
                    System.out.println("exit by stop me");
                    break;
                }

                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            ChangeObjectThreadSafe t = new ChangeObjectThreadSafe();
            t.start();
            Thread.sleep(150);
//            t.stop();
            t.stopMe();
        }
    }
}
/*
User{id=1493165973, name='1493165972'}
User{id=1493165973, name='1493165972'}
 */