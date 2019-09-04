package com.light.blog.core.service.msg;

import com.light.blog.common.utils.BeanUtils;
import com.light.blog.core.service.email.TemplateEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * <p>
 * 发送消息相关的邮件通知
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@Component
public class MsgEmailNotifier {

    @Autowired
    @Qualifier("nonWebTemplateEngine")
    TemplateEngine templateEngine;

    @Autowired
    TemplateEmailSender emailSender;

    public Future<Boolean> sendReplyEmail(String to, ReplyTemplateModel model) {
        String subject = "有人回复了你的评论";
        String key = "email/reply.html";
        @SuppressWarnings("unchecked")
        Map<String, Object> params = BeanUtils.copyAs(model, Map.class);
        return emailSender.sendHTML(to, subject, key, params);
    }

}
