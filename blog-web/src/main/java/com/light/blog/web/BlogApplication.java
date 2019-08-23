package com.light.blog.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @auther: light
 * @since: 2018/8/30 22:36
 * <p></p>
 */
@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true) //使用cglib,避免jdk proxy导致只能注入接口类型bean
public class BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BlogApplication.class, args);

    }
}
