package com.light.blog.web.config.toolkit;

import java.lang.annotation.*;

/**
 * <p>
 *  无需登陆即可访问
 * </p>
 *
 * @author light
 * @since 2019/2/11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuestApi {
}
