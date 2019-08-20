package com.light.blog.core.test.service;

import com.light.blog.common.utils.JsonUtils;
import com.light.blog.core.service.msg.MsgCommentService;
import com.light.blog.core.service.msg.MsgCommentVo;
import com.light.blog.core.test.CoreTestApplication;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

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
public class MsgCommentServiceTest {

    @Autowired
    MsgCommentService commentService;

    @Test
    public void query() {

        QueryMsgCommentVo q = new QueryMsgCommentVo().setArticleKey("foo").setRefDepth(2);
        q.setSize(1).setIndex(5);

        Object obj = commentService.query(q);
        System.out.println(JsonUtils.toJsonString(obj));
    }

    @Test
    public void add() {
        commentService.add(
                new MsgCommentVo().setContent("tmp content")
                        .setEmail("123@tmp.com")
                        .setIsDeleted(false)
                        .setNickname("stupid")
                        .setArticleKey("foo")
                        .setPostDate(LocalDateTime.now())
        );
    }

}
