package com.light.blog.core.service.email.mq;

import com.light.blog.common.thread.MyThreadFactory;
import com.light.blog.core.service.email.EmailSender;
import com.light.blog.core.service.email.EmailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>
 * 邮件消息队列消费者
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
//@Component
@Slf4j
@Deprecated
public class EmailConsumer implements InitializingBean {

    @Autowired
    EmailSender emailSender;

    @Autowired
    EmailMQ emailMQ;

    ExecutorService executorService = Executors.newSingleThreadExecutor(new MyThreadFactory("邮件工人")); //TODO 暂时没必要"可配置",如果以后还有其他地方需要线程池,在BeanConfig里注册,并使用全局配置(而不是只针对邮件)

    @Override
    public void afterPropertiesSet() {

        Runnable runnable = () -> {
            while (true) {
                try {
                    emailMQ.consume((emailVo) -> emailSender.send(emailVo)); //勤劳的工人一刻不停的等待着
                }catch (Throwable th){
                    log.error("发送邮件时发生异常",th);
                }
            }
        };
        //这里只启用一个工人 以后根据需要调整线程池大小 和邮件工人数量
        executorService.execute(runnable);

        BlockingDeque<EmailVo> queue =  new LinkedBlockingDeque<>();


    }
}

