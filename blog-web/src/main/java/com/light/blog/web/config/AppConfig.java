package com.light.blog.web.config;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static com.light.blog.common.utils.CheckUtils.isNotEmpty;
import static org.apache.commons.lang3.ArrayUtils.contains;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/4
 */
@ConfigurationProperties(prefix = "app")
@Configuration
@Data
public class AppConfig {

    String url;
    String contextPath;
    String uploadPath;
    CookieConfig cookie;
    String[]allowOrigins;


    @Autowired
    Environment env;

    public boolean isProduction(){
        String[] activeProfiles = env.getActiveProfiles();
        return isNotEmpty(activeProfiles) && contains(activeProfiles, "pro");
    }

    public boolean isAllowOrigin(String origin){
        return ArrayUtils.contains(allowOrigins,origin);
    }
}
