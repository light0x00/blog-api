package com.light.blog.web.config.aop;

import com.light.blog.web.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/8
 */
@Component
@Slf4j
public class CorsInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AppConfig appConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        cors(request,response);
        return true;
    }

    private void cors(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {

        String targetOrigin = request.getHeader("Origin");
//
        if (!appConfig.isAllowOrigin(targetOrigin)) {
            log.info("The {} Origin is not allowed", targetOrigin);
            return;
        }
        response.setHeader("Access-Control-Allow-Origin", targetOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,PUT,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");  //跨域默认不允许 Content-Type: application/json;charset=UTF-8
        /* Access-Control-Allow-Headers 允许前端(JS)附带哪些自定义请求头*/
        /* Access-Control-Expose-Headers 允许前端(JS)访问哪些响应头
            默认在跨域情况下前端只能访问:Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma */
    }

}
