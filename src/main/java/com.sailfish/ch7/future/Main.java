package com.sailfish.ch7.future;

/**
 * @author sailfish
 * @create 2017-05-09-下午9:20
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据=" + data.getResult());
    }
}
