package com.sailfish.ch7.future;

/**
 * @author sailfish
 * @create 2017-05-09-下午9:18
 */
public class Client {

    public Data request(final String queryStr) {
        final FutureData data = new FutureData();
        new Thread(){

            //realData构造很慢，需要在单独的线程中执行
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                data.setResult(realData);
            }
        }.start();
        //FutureData会立即返回
        return data;
    }
}
