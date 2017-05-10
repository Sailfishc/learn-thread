package com.sailfish.asynctest;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟同步调用
 * @author sailfish
 * @create 2017-05-10-上午9:16
 */
public class SyncTest {

    public static void main(String[] args) throws InterruptedException {
        // 模拟调用
        RpcService rpcService = new RpcService();
        HttpService httpService = new HttpService();
        long start = System.currentTimeMillis();
        rpcService.getRpcResult();
        httpService.getHttpResult();
        System.out.println("sync speed time:" + (System.currentTimeMillis() - start));
    }

    static class RpcService{
        Map<String, String> getRpcResult() throws InterruptedException {
            Thread.sleep(100);
            return new HashMap<String, String>();
        }
    }

    static class HttpService{

        Integer getHttpResult() throws InterruptedException {
            Thread.sleep(200);
            return 0;
        }
    }
}
