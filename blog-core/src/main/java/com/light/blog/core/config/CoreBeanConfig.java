package com.light.blog.core.config;

import com.light.blog.dao.config.DaoBeanConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/2/2
 */
@Configuration
@ComponentScan(
        value = {"com.light.blog.core.domain", "com.light.blog.core.service", "com.light.blog.core"}, basePackageClasses = DaoBeanConfig.class
)
public class CoreBeanConfig {



}
