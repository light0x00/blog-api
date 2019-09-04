package com.light.blog.core.service.email.mq;

import com.light.blog.core.service.email.EmailVo;
import com.light.blog.core.service.email.mq.EmailMQ;
import com.light.blog.core.utils.PrincipalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * <p>
 *  邮件消息队列生产者
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
//@Component
@Slf4j
@Deprecated
public class TemplateEmailProducer {


    @Autowired
    EmailMQ emailMQ;

    @Autowired
    @Qualifier("nonWebTemplateEngine")
    TemplateEngine templateEngine;



    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * 发送html模版邮件,投递到邮件消息队列
     * @param to
     * @param subject
     * @param templateKey
     */
    public void sendHTML(String to,String subject,String templateKey,Context ctx){
        final String htmlContent = this.templateEngine.process(templateKey, ctx);
        EmailVo emailVo =new EmailVo(to,subject,htmlContent,true);
//        emailMQ.produce(emailVo);

    }


    public void sendHTML(String to,String subject,String templateKey,Map<String,Object> model) {
        final Context ctx = new Context(PrincipalContext.getLocale());
        ctx.setVariables(model);
        sendHTML(to,subject,templateKey,ctx);
    }

}
