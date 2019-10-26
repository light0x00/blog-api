package com.light.blog.common.utils;

import com.light.blog.common.exceptions.BusinessException;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/2/1
 */
public class Assert {

    public static void notNull(Object obj, String msg, Object... replacePatterns) {
        isTrue(obj != null, msg, replacePatterns);
    }

    public static void notNull(Object obj, RuntimeException exp) {
        if (obj == null) {
            throw exp;
        }
    }

    public static void isTrue(boolean condition, String msg, Object... replacePatterns) {
        if (!condition) {
            throw new BusinessException(msg, replacePatterns);
        }
    }

    public static void isTrue(boolean condition, RuntimeException exp) {
        if (!condition) {
            throw exp;
        }
    }

    public static void isTrue(boolean condition) {
        if (!condition) {
            throw new BusinessException("Assert failed with %s", condition);
        }
    }
}
