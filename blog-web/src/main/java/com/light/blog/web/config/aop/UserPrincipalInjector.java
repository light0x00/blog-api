package com.light.blog.web.config.aop;

import com.light.blog.web.config.AppConfig;
import com.light.blog.web.config.CookieConfig;
import com.light.blog.core.utils.UserEncryptionUtils;
import com.light.blog.core.utils.UserPrincipalContext;
import com.light.blog.dao.entities.UserVo;
import com.light.blog.web.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther: light
 * @since: 2018/10/6 11:38
 * <p></p>
 */
@Slf4j
@Component
public class UserPrincipalInjector extends HandlerInterceptorAdapter {


    @Autowired
    CookieUtils cookieUtils;

    CookieConfig cookieConfig;
    @Autowired
    public void setAppConfig(AppConfig appConfig){
        this.cookieConfig = appConfig.getCookie();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //静态请求不需要注入
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = cookieUtils.read(cookieConfig.getTokenKey());
        if (token != null) {
            UserVo u = UserEncryptionUtils.decode(token);
            if (u != null) {
                log.debug("注入用户信息{}", u);
                UserPrincipalContext.set(u);
                cookieUtils.write(cookieConfig.getTokenKey(),token);  //刷新cookie失效
            } else {
                log.debug("cookie中找到了用户token,但是解析失败");
                UserPrincipalContext.set(null);
            }
        } else {
            UserPrincipalContext.set(null);
            log.debug("cookie中没有找到用户token");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
