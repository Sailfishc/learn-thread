package com.sailfish.ch7.future;

/**
 * @author sailfish
 * @create 2017-05-09-下午9:09
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String result) {
        //构造可能很慢，需要用户等待很久，这里使用sleep模拟
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(result);
            try {
                //这里使用sleep代替一个很慢的操作
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    public String getResult() {
        return result;
    }
}
