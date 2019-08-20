package com.light.blog.web.config.aop;

import com.light.blog.common.vo.OutputModel;
import com.light.blog.common.vo.ResponseStatus;
import com.light.blog.web.config.toolkit.WebUtils;
import com.light.blog.core.domain.UserDomain;
import com.light.blog.core.utils.UserPrincipalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.light.blog.web.config.AppConfig;

/**
 * @auther: light
 * @since: 2018/10/1 18:39
 * <p></p>
 */

@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {




    @Autowired
    UserDomain userDomain;


    @Autowired
    AppConfig appConfig;

    @Autowired
    WebUtils webUtils;

    public AuthInterceptor() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.trace(request.getRequestURI() + "=>" + handler.getClass());

        //1. 静态资源
        if (handler instanceof ResourceHttpRequestHandler) {
            log.trace("静态资源放行");
            return true;
        }

        //2. 动态请求
        else if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            //2.1. 错误页面放行
            if ("/error".equals(request.getServletPath())) {
                log.trace("error放行");
                return true;
            }
            //2.2 来自swagger的请求放行
            if (!appConfig.isProduction() && webUtils.isSwagger(request.getHeader("Referer"))) {
                log.trace("swagger放行");
                return true;
            }

            //2.3 访问adminApi却不是管理员
            if (webUtils.isAdminApi(hm) && !userDomain.isAdministrator()) {
                webUtils.writeJson(response, OutputModel.ofWarn(ResponseStatus.NoAccess));
                return false;
            }

            //2.4 访问guest直接放行
            if (webUtils.isGuestPage(hm)) {
                log.trace("guestPage放行");
                return true;
            }

            //2.5 访问普通api 检查是否登陆
            if (!UserPrincipalContext.isLogin()) {
                webUtils.writeJson(response, OutputModel.ofWarn(ResponseStatus.NotLogin));
                return false;
            }
            return true;

        }
        //3. 未知的
        else {
            log.error("未处理的 请求 Handler 类型" + handler.getClass());
        }
        return true;
    }



    /* ----------------------------------------------------- 工具 ----------------------------------------------------- */



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

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
