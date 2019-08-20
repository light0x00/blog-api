package com.light.blog.core.utils;

import com.light.blog.dao.entities.UserVo;
import com.light.blog.common.exceptions.BusinessException;

/**
 * @auther: light
 * @since: 2018/10/5 20:00
 * <p></p>
 */
public class UserPrincipalContext {

    private static ThreadLocal<UserVo> userThreadLocal = new ThreadLocal<>();

    public static void set(UserVo user) {
        userThreadLocal.set(user);
    }

    public static UserVo get() {
        return userThreadLocal.get();
    }

    public static UserVo getRequird() {
        UserVo u = userThreadLocal.get();
        if(u==null){
            throw new BusinessException(("未登录!"));
        }
        return u;
    }

    public static boolean isLogin() {
        return get() != null;
    }

}
