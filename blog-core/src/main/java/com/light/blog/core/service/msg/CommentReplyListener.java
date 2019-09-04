package com.light.blog.core.service.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  监听回复评论事件
 * </p>
 *
 * @author light
 * @since 2019/9/4
 */
@Component
public class CommentReplyListener implements ApplicationListener<CommentReplyEvent> {

    @Autowired
    MsgEmailNotifier emailNotifier;

    @Override
    public void onApplicationEvent(CommentReplyEvent event) {
        //发邮件通知
        String toEmail = event.getRefUserEmail();
        ReplyTemplateModel replyTemplateModel = new ReplyTemplateModel();
        replyTemplateModel.setReplyContent(event.getReplyContent());
        replyTemplateModel.setResponder(event.getUsername());
        replyTemplateModel.setReplyUrl(event.getReplyUrl());
        emailNotifier.sendReplyEmail(toEmail,replyTemplateModel);
    }
}
