package com.light.blog.web.config.toolkit;

import java.lang.annotation.*;

/**
 * <p>
 *  只有管理员才可以访问的web接口
 * </p>
 *
 * @author light
 * @since 2019/5/23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminApi {
}
