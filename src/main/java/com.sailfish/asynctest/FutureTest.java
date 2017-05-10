package com.sailfish.asynctest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * future模式模拟
 * @author sailfish
 * @create 2017-05-10-上午9:22
 */
public class FutureTest {

    final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        //模拟future调用
        //todo 什么时候会有这种同时的调用呢?
        RpcService rpcService = new RpcService();
        HttpService httpService = new HttpService();
        Future<Map<String, String>> future1 = null;
        Future<Integer> future2 = null;

        try {
            //todo 待研究JDK8新特性
            future1 = EXECUTOR_SERVICE.submit(() -> rpcService.getRpcResult());
            future2 = EXECUTOR_SERVICE.submit(() -> httpService.getHttpResult());

            long start = System.currentTimeMillis();
            //耗时100ms
            Map<String, String> result1 = future1.get(300, TimeUnit.SECONDS);
            //耗时200ms
            Integer result2 = future2.get(300, TimeUnit.SECONDS);

            System.out.println("future speed time:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            if (future1 != null) {
                future1.cancel(true);
            }
            if (future2 != null){
                future2.cancel(true);
            }
            throw new RuntimeException(e);
        }

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
