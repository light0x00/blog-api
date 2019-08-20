package com.light.blog.web.test.other;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        MyCountDownLatch latch =new MyCountDownLatch(3);

        Runnable r = ()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(Thread.currentThread().getId()+" count down...");
            latch.countDown();
        };
        for (int i = 0; i < 4; i++) {
            new Thread(r).start();
        }

        latch.await();
        System.out.println("end..");

    }
}