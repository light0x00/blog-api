package com.light.blog.core.test.service.msg;

import com.light.blog.core.service.msg.MsgEmailNotifier;
import com.light.blog.core.service.msg.ReplyTemplateModel;
import com.light.blog.core.test.CoreTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreTestApplication.class)
@Component
public class MsgMailNotifierTest {

    @Autowired
    MsgEmailNotifier msgEmailDomain;

    @Test
    public void t() throws InterruptedException {
        ReplyTemplateModel model = new ReplyTemplateModel();
        model.setReplyContent("test reply content");
        model.setReplyUrl("https://blog.light0x00.com/");
        model.setResponder("Test Responder");
        msgEmailDomain.sendReplyEmail("light0x00@163.com",model);
        Thread.sleep(1000);
        msgEmailDomain.sendReplyEmail("light0x00@163.com",model);
        Thread.sleep(20000);
    }


}
