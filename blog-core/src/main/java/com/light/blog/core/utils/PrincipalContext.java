package com.light.blog.core.utils;

import java.util.Locale;

/**
 * @auther: light
 * @since: 2018/10/5 20:00
 * <p></p>
 */
public class PrincipalContext {

    private static ThreadLocal<PrincipalVo> userThreadLocal =ThreadLocal.withInitial(PrincipalVo::new);

    static {
    }

    public static void setLocale(Locale locale) {
        userThreadLocal.get().setLocale(locale);
    }

    public static Locale getLocale(){
        return userThreadLocal.get().getLocale();
    }

}
