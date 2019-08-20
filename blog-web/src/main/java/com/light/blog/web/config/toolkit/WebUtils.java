package com.light.blog.web.config.toolkit;

import com.light.blog.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
@Component
public class WebUtils {

    @Value("${server.servlet.context-path}/swagger-ui.html")
    private String swaggerPath;

    public void writeJson(HttpServletResponse response, Object data) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(data instanceof String ? data.toString() : JsonUtils.toJsonString(data));
        writer.flush();
        writer.close();
    }



    public boolean isSwagger(String referer) throws MalformedURLException {
        return referer != null && swaggerPath.equals(new URL(referer).getPath());
    }

    public boolean isGuestPage(HandlerMethod hm) {
        return hasAnnotation(hm, GuestApi.class);
    }

    public boolean isAdminApi(HandlerMethod hm) {
        return hasAnnotation(hm, AdminApi.class);
    }

    private <A extends Annotation> boolean hasAnnotation(HandlerMethod hm, Class<A> annotationType) {
        A methodAnnotation = hm.getMethodAnnotation(annotationType);
        if (null != methodAnnotation) {
            return true;
        } else {
            Class<?> beanType = hm.getBeanType();
            A beanAnnotation = beanType.getAnnotation(annotationType);
            if (null != beanAnnotation) {
                return true;
            }
        }
        return false;
    }

}
