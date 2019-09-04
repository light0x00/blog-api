package com.light.blog.core.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class AsyncEmailSender {

    @Autowired
    @Qualifier("emailThreadPoolExecutor")
    private ThreadPoolExecutor executor;

    @Autowired
    private EmailSender emailSender;

    public Future<Boolean> send(EmailVo emailVo) {
       return  executor.submit(() -> {
           try {
               emailSender.send(emailVo); //勤劳的工人一刻不停的等待着
               return true;
           } catch (Throwable th) {
               log.error("发送邮件时发生异常", th);
               return false;
           }
       });
    }

}
