package com.light.blog.web.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.google.common.base.Predicates;
import com.light.blog.core.config.CoreBeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 * @auther: light
 * @since: 2018/8/31 10:01
 * <p></p>
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = CoreBeanConfig.class, basePackages = {"com.light.blog.web"})
public class WebBeanConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("my blog api")
                .description("")
                .contact(new Contact("light", "blog.light0x00.com", "light0x00@gmail.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    /*
        mybatisplus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /*
        事务
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }


}
