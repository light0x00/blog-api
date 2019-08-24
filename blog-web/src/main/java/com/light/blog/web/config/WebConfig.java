package com.light.blog.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.light.blog.web.config.aop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @auther: light
 * @since: 2018/8/30 22:35
 * <p></p>
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    Environment environment;

    @Autowired
    ApplicationContext ctx;

    @Autowired
    AppConfig appConfig;


    @Autowired
    UserPrincipalInjector userPrincipalInjector;

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    CorsInterceptor corsInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).order(0);
        registry.addInterceptor(userPrincipalInjector).addPathPatterns("/**").order(1);
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login").order(2);

    }



    /**
     * 请求静态资源映射
     * path
     * spring.mvc.static-path-pattern=/static/**，
     * directory
     * spring.resources.static-locations=classpath:/static
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/static/**").addResourceLocations("file:/Users/light/IdeaProjects/personal-projects/gc-shop/src/main/resources/static/"); //这样可以防止资源被缓存,每次都会读硬盘里最新的文件
//        registry.addResourceHandler("/files/**").addResourceLocations("file:" + environment.getProperty("app.upload-path")); //这样可以防止资源被缓存,每次都会读硬盘里最新的文件
//        registry.addResourceHandler("/files/**").addResourceLocations("file:" + appConfig.getUploadPath()); //这样可以防止资源被缓存,每次都会读硬盘里最新的文件
//        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/img/");

        //映射swagger-ui
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/springfox-swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
//        registry
//                .addMapping("/**")
//                .allowedOrigins(appConfig.getAllowOrigins())
//                .allowCredentials(true)
//                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders()
//                .maxAge(3600);
    }


    @Override
    protected void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new MyConverter());
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new LocalDateTimeFormatter());
    }

    @Autowired
    Jackson2ObjectMapperBuilder jacksonBuilder;

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ObjectMapper objectMapper = jacksonBuilder.build();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }


}
