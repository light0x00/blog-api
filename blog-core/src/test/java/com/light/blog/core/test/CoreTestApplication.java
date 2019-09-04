package com.light.blog.core.test;

import com.light.blog.common.spring.ext.YamlPropertySourceFactory;
import com.light.blog.core.config.CoreBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.TestPropertySource;
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
@ComponentScan(basePackageClasses = {CoreBeanConfig.class})
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource(value = {"classpath:email.properties"}, encoding = "utf-8")
@PropertySource(value = {"classpath:template-engine.yaml","classpath:application.yml"},factory = YamlPropertySourceFactory.class, encoding = "utf-8")
public class CoreTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoreTestApplication.class, args);
    }

}
