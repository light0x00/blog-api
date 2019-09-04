package com.light.blog.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/4
 */
public class MyThreadFactory implements ThreadFactory {

    AtomicInteger counter = new AtomicInteger(0);

    String threadNamePrefix;

    public MyThreadFactory(String threadNamePrefix){
        this.threadNamePrefix=threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return  new Thread(r,threadNamePrefix+"-"+counter.incrementAndGet());
    }
}
