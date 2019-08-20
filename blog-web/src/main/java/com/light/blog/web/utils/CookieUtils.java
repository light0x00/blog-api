package com.light.blog.web.utils;


import com.light.blog.web.config.AppConfig;
import com.light.blog.web.config.CookieConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @auther: light
 * @since: 2018/10/5 15:17
 * <p></p>
 */
@Slf4j
@Component
public class CookieUtils {

    CookieConfig config;
    @Autowired
    public void config(AppConfig appConfig){
        this.config = appConfig.getCookie();
    }

    public String read(String key) {

        ServletRequestAttributes s = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = s.getRequest();

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(key)) {
                try {
                    return URLDecoder.decode(cookies[i].getValue(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    log.error("encode \"" + cookies[i].getValue() + "\"时发生异常", e);
                }
            }
        }
        return null;
    }

    public void write(String key, String val) {
        write(key,val,config.getDomain(),config.getPath(),config.getMaxAge());
    }

    public void write(String key, String val, String domain,String path,int maxAge) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        try {
            Cookie c = new Cookie(key, URLEncoder.encode(val, "utf-8"));
            c.setDomain(domain);
            c.setPath(path);
            c.setMaxAge(maxAge);
            response.addCookie(c);
        } catch (UnsupportedEncodingException e) {
            log.error("encode \"" + val + "\"时发生异常", e);
        }
    }

    public void remove(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(key)) {

                    cookies[i].setMaxAge(0);
                    cookies[i].setDomain(config.getDomain());
                    cookies[i].setPath(config.getPath());

                    response.addCookie(cookies[i]);
                    return;
                }
            }
        }
        log.warn("移除cookie({})时没有找到目标");
    }

}
