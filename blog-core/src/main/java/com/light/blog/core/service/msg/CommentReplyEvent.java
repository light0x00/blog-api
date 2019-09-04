package com.light.blog.core.service.msg;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/4
 */
@Data
@Accessors(chain = true)
public class CommentReplyEvent extends ApplicationEvent {

    //回复用户信息
    String username;
    String replyContent;
    String replyUrl;

    //被回复用户信息
    String refUsername;
    String refUserEmail;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})  相当于js中的event.target
     */
    public CommentReplyEvent(Object source){
        super(source);
    }

    public CommentReplyEvent(Object source,String username, String replyContent, String replyUrl, String refUsername, String refUserEmail) {
        super(source);
        this.username = username;
        this.replyContent = replyContent;
        this.replyUrl = replyUrl;
        this.refUsername = refUsername;
        this.refUserEmail = refUserEmail;
    }
}
