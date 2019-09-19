package com.light.blog.core.config;

import com.light.blog.common.spring.ext.YamlPropertySourceFactory;
import com.light.blog.common.thread.MyThreadFactory;
import com.light.blog.dao.config.DaoBeanConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/2/2
 */
@Configuration
@ComponentScan(value = {"com.light.blog.core"}, basePackageClasses = DaoBeanConfig.class)
@PropertySource(value = {"classpath:template-engine.yaml"}, factory = YamlPropertySourceFactory.class, encoding = "utf-8")
@Slf4j
public class CoreBeanConfig {


    @Autowired
    EmailConfig emailConfig;

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(emailConfig.getHost());
        sender.setPort(emailConfig.getPort());
        sender.setUsername(emailConfig.getUsername());
        sender.setPassword(emailConfig.getPassword());
        sender.setDefaultEncoding(emailConfig.getEncoding());

        Properties rawConfig = new Properties();
        rawConfig.put("mail.smtp.timeout",emailConfig.getTimeout());
        rawConfig.put("mail.smtp.connectiontimeout",emailConfig.getConnectionTimeout());
        sender.setJavaMailProperties(rawConfig);
        return sender;
    }

    @Bean
    public ThreadPoolExecutor emailThreadPoolExecutor() {
        BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES, queue, new MyThreadFactory("邮件工人"), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.error("发送邮件请求过于频繁,已超出队列上限!");
            }
        });
        return executor;
    }
}
