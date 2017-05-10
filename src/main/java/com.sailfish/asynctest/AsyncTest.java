package com.sailfish.asynctest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * callback方式
 * @author sailfish
 * @create 2017-05-10-上午9:39
 */
public class AsyncTest {
    public static HttpAsyncClient httpAsyncClient;

    public static CompletableFuture<String> getHttpData(String url) {
        CompletableFuture future = new CompletableFuture();
        //创建一个异步的post生产者
        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(new HttpPost(url));
        BasicAsyncResponseConsumer consumer = new BasicAsyncResponseConsumer();

        FutureCallback futureCallback = new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                future.complete(httpResponse);
            }

            @Override
            public void failed(Exception e) {
                future.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                future.cancel(true);
            }
        };
        httpAsyncClient.execute(producer, consumer, futureCallback);
        return future;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = AsyncTest.getHttpData("http://www.jd.com");
        //todo 测试没通过
        String result = future.get();
//        System.out.println(result);
    }

}
