package com.light.blog.core.service.email.mq;

import com.light.blog.common.thread.MQ;
import com.light.blog.core.config.EmailConfig;
import com.light.blog.core.service.email.EmailVo;

/**
 * <p>
 *  邮件消息队列
 * </p>
 *
 * @author light
 * @since 2019/9/4
 */
//@Component
@Deprecated
public class EmailMQ extends MQ<EmailVo> {

    public EmailMQ(EmailConfig emailConfig) {
        super(emailConfig.getQueueSize());
    }
}
