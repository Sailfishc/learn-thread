package com.sailfish.high_perform.chapter19.future;

/**
 * @author sailfish
 * @create 2019-01-30-8:21 PM
 */
public interface FutureTask {

    /**
     *
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);
}
