package com.light.blog.web.config.aop;

import com.light.blog.web.config.AppConfig;
import com.light.blog.web.config.CookieConfig;
import com.light.blog.core.utils.PrincipalContext;
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

        PrincipalContext.setLocale(request.getLocale());

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
