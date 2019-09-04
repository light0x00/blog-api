package com.light.blog.core.service.email;

import com.light.blog.core.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
@Service
@Slf4j
public class EmailSender {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

    public void send(EmailVo emailVo) {

        String to=emailVo.getTo();
        String subject=emailVo.getSubject();
        String content=emailVo.getContent();
        boolean html=emailVo.isHtml();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,emailConfig.getEncoding());
            messageHelper.setFrom(emailConfig.getUsername(),emailConfig.getNickname());
            messageHelper.setText(content,html);
            messageHelper.setSubject(subject);
            messageHelper.setTo(to);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("发送邮件失败",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码失败",e);
        }
        log.debug("发送邮件完成!");
    }


}
