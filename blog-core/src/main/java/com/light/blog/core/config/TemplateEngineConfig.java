package com.light.blog.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@Data
@ConfigurationProperties(prefix = "app.template")
@Component
public class TemplateEngineConfig {

    String i18nPrefix;
    boolean cache;
    String encoding;

    Set<String> patterns;
    String prefix;
    String suffix;




}
