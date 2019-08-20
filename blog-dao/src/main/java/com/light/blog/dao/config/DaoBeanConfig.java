package com.light.blog.dao.config;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "com.light.blog.dao.mapper")
public class DaoBeanConfig {
}
