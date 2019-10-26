package com.light.blog.core.test.service.email;

import com.light.blog.core.service.email.AsyncTemplateEmailSender;
import com.light.blog.core.test.CoreTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;

import java.util.Locale;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreTestApplication.class)
@Component
public class TemplateEmailProducerTest {

    @Autowired
    AsyncTemplateEmailSender templateEmailSender;

    @Test
    public void t() throws InterruptedException {
        Context ctx = new Context(Locale.getDefault());

        ctx.setVariable("replyContent", "test reply content");
        ctx.setVariable("replyUrl", "https://blog.light0x00.com/");
        ctx.setVariable("responder", "Test Responder");

        templateEmailSender.sendHTML("light0x01@ff.com", "Test", "email/reply.html", ctx);
//        Thread.sleep(1000);
        templateEmailSender.sendHTML("light0x00@163.com", "Test", "email/reply.html", ctx);
        templateEmailSender.sendHTML("light0x00@163.com", "Test", "email/reply.html", ctx);
        Thread.sleep(20000);

    }


}
