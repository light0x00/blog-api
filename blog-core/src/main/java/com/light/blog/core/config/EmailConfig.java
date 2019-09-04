package com.light.blog.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@Data
@ConfigurationProperties(prefix = "app.email")
@Component
@Configuration
public class EmailConfig {

    //邮件服务配置
    String host;
    int port;
    String username;
    String nickname;
    String password;
    String encoding;

    //邮件消息队列大小
    int queueSize;
}
