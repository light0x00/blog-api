package com.light.blog.web.test.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther: light
 * @since: 2018/10/19 17:54
 * <p></p>
 */
@Slf4j
public class ReadWriteLockTest {

    static ReadWriteLock lock = new ReentrantReadWriteLock();


    public static void main(String[] args) {
//        Runnable r = () -> {
//            lock.readLock();
//            System.out.println(Thread.currentThread() + " get read lock");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread() + " will get write lock");
//            lock.writeLock();
//        };
//
//        for (int i = 0; i < 10; i++) {
//            new Thread(r).start();
//        }




        //

    }

    @Test
    public void t1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.writeLock().lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.writeLock().unlock();
        }
        );

        Thread t2 = new Thread(
                ()->{
                    log.info("try get readLock");
                    lock.readLock().lock();
                    log.info("get readLock");
                    lock.readLock().unlock();
                }
        );

        t1.start();
        Thread.sleep(10);
        t2.start();
        t1.join();
        t2.join();
        //写锁被占用时拿读锁阻塞
    }


    @Test
    public void t2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.readLock().lock();
            log.info("readLock lock");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.readLock().unlock();
            log.info("readLock unlock");
        }
        );

        Thread t2 = new Thread(
                ()->{
                    log.info("try get writeLock");
                    lock.writeLock().lock();
                    log.info("get writeLock");
                    lock.writeLock().unlock();
                }
        );

        t1.start();
        Thread.sleep(10);
        t2.start();
        t1.join();
        t2.join();
        //读锁被占用时拿写锁阻塞
    }

}
