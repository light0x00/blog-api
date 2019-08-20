package com.light.blog.web.test.other;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCountDownLatch {

    private volatile int count;

    public MyCountDownLatch(int count){
        if(count<0){
            throw new IllegalArgumentException("count < 0");
        }
        this.count =count;

    }
    private Object lock = new Object();

    public void countDown(){
            //等于0的时候 就唤醒 所有调用await的线程
            if (count == 0) {
                log.info("唤醒所有调用await的线程");
                lock.notifyAll();
            }else {
                log.info(Thread.currentThread().toString()+"countdown");
                count--;
            }
    }

    public void await() throws InterruptedException {
        synchronized (lock) {
            if(count!=0) {
                log.info(Thread.currentThread()+"被挂起...");
                lock.wait();
            }
        }
    }

}