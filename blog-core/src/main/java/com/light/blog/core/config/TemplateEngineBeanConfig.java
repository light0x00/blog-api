package com.light.blog.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Collections;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@Configuration
public class TemplateEngineBeanConfig {

    @Autowired
    TemplateEngineConfig templateConfig;


    /**
     * 邮件国际化
     * @return
     */
    @Bean
    public ResourceBundleMessageSource i18nBundle() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(templateConfig.getI18nPrefix()); //国际化资源前缀
        messageSource.setDefaultEncoding(templateConfig.getEncoding());
        return messageSource;
    }

    /**
     * 模版引擎(Non-Web-Environment)
     * @return
     */
    @Bean
    public TemplateEngine nonWebTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for TEXT emails
        templateEngine.addTemplateResolver(textTemplateResolver());
        // Resolver for HTML emails (except the editable one)
//        templateEngine.addTemplateResolver(htmlTemplateResolver());
        // Resolver for HTML editable emails (which will be treated as a String)
//        templateEngine.addTemplateResolver(stringTemplateResolver());
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(i18nBundle());
        return templateEngine;
    }

    /**
     * 处理文本模版。 优先级1
     * @return
     */
    private ITemplateResolver textTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setResolvablePatterns(templateConfig.getPatterns());
        templateResolver.setPrefix(templateConfig.getPrefix());
        templateResolver.setSuffix(templateConfig.getSuffix());

        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding(templateConfig.getEncoding());
        templateResolver.setCacheable(templateConfig.isCache());
        return templateResolver;
    }

    /**
     * 处理字符串。优先级3
     * @return
     */
    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(templateConfig.isCache());
        return templateResolver;
    }

}
