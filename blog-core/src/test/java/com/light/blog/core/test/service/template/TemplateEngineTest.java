package com.light.blog.core.test.service.template;

import com.light.blog.core.test.CoreTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 *  模版语法
 *  https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#textual-syntax
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreTestApplication.class)
@Component
public class TemplateEngineTest {

    @Autowired
    @Qualifier("nonWebTemplateEngine")
    SpringTemplateEngine templateEngine;



    @Test
    public void t1() {

        final Context ctx = new Context(Locale.CHINESE);
        ctx.setVariable("name", "Jack");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("imageResourceName", "https://blog.light0x00.com/favicon.ico");

        final String htmlContent = this.templateEngine.process("email/demo.html", ctx);
        final String htmlContent2 = this.templateEngine.process("email/demo.txt", ctx);
        System.out.println(htmlContent);
        System.out.println(htmlContent2);

    }

    @Test
    public void t2() {

        final Context ctx = new Context(Locale.CHINESE);
        ctx.setVariable("responder", "Jack");
        ctx.setVariable("replyContent", "hallo!");
        ctx.setVariable("replyUrl", "www.baidu.com");

        final String htmlContent = this.templateEngine.process("email/reply.html", ctx);
        System.out.println(htmlContent);

    }

}
