package com.light.blog.common.thread;


import com.light.blog.common.utils.Colors;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayDeque;
import java.util.function.Consumer;

/**
 * <p>
 * 有界阻塞队列
 * </p>
 *
 * @author light
 * @since 2019/3/3
 */
@Slf4j
public class MQ<T> {

    private ArrayDeque<T> queue = new ArrayDeque<>();
    int maxSize;

    public MQ(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void produce(T message) {
        while (queue.size() >= maxSize) {
            try {
                log.debug(Colors.red(Thread.currentThread().getName() + ":wait..."));
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(message);
        log.debug(Colors.red(Thread.currentThread().getName() + "添加了消息:" + message));
        this.notifyAll();
    }

    public synchronized void consume(Consumer<T> consumer) {
        while (queue.size() == 0) {
            try {
                log.debug(Colors.blue(Thread.currentThread().getName() + ":wait..."));
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        log.debug(Colors.blue(Thread.currentThread().getName() + "running"));
        T message = queue.poll();
        consumer.accept(message);
        log.debug(Colors.blue(Thread.currentThread().getName() + "处理了消息:" + message));
        this.notifyAll();
    }



}
