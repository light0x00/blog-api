package com.light.blog.dao.test.mapper;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/23
 */

import com.light.blog.dao.entities.MsgComment;
import com.light.blog.dao.mapper.MsgCommentMapper;
import com.light.blog.dao.test.DaoTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DaoTestApplication.class)
@Component
public class MsgCommontMapperTest {

    @Autowired
    MsgCommentMapper commentMapper;

    @Test
    public void t0() {
        List lst = commentMapper.selectList(null);
        System.out.println(lst);
    }

    @Test
    public void t1() {
        commentMapper.insert(
                new MsgComment()
                        .setContent("tmp content")
                        .setEmail("123@tmp.com")
                        .setIsDeleted(false)
                        .setNickname("foo")
                        .setPostDate(LocalDateTime.now()));
    }

    @Test
    public void t2(){
//        List lst = commentMapper.selectByXml("foo");
//        System.out.println(lst);
    }
}
