package com.light.blog.core.test;

import com.light.blog.core.config.CoreBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2018/12/19
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {CoreBeanConfig.class},basePackages = "com.light.blog.core.test")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class CoreTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoreTestApplication.class, args);
    }

}
