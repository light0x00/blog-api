package com.light.blog.dao.test;

import com.light.blog.dao.config.DaoBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2018/12/19
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {DaoBeanConfig.class})
public class DaoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoTestApplication.class, args);
    }

}
