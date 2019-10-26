package com.light.blog.core.service.email;

import com.light.blog.core.utils.PrincipalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * <p>
 *  邮件消息队列生产者
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@Component
@Slf4j
public class AsyncTemplateEmailSender {

    @Autowired
    @Qualifier("nonWebTemplateEngine")
    TemplateEngine templateEngine;

    @Autowired
    AsyncEmailSender asyncEmailSender;

    /**
     * 发送html模版邮件,投递到邮件消息队列
     * @param to
     * @param subject
     * @param templateKey
     */
    public Future<Boolean> sendHTML(String to,String subject,String templateKey,Context ctx){
        final String htmlContent = this.templateEngine.process(templateKey, ctx);
        EmailVo emailVo =new EmailVo(to,subject,htmlContent,true);
        return asyncEmailSender.send(emailVo);
    }


    public Future<Boolean> sendHTML(String to,String subject,String templateKey,Map<String,Object> model) {
        final Context ctx = new Context(PrincipalContext.getLocale());
        ctx.setVariables(model);
        return sendHTML(to,subject,templateKey,ctx);
    }

}
