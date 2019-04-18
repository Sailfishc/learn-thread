package com.sailfish.high_perform.chapter19.future;

/**
 * @author sailfish
 * @create 2019-01-30-8:20 PM
 */
public interface Future<T> {

    /**
     * 获取线程结果
     *
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;

    /**
     * 线程任务是否已经结束
     *
     * @return
     */
    boolean isDone();
}
