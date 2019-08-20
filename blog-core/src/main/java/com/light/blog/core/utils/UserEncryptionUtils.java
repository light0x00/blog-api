package com.light.blog.core.utils;

import com.light.blog.dao.entities.UserVo;
import com.light.blog.common.utils.JsonUtils;

import java.util.Base64;

/**
 * <p>
 *     加密、解密
 * </p>
 * @auther: light
 * @since: 2018/10/5 16:36
 */
public class UserEncryptionUtils {


    public static UserVo decode(String token) {
        try {
            return JsonUtils.parseJson(decodeBase64(token), UserVo.class);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static String encode(UserVo u) {
        return encodeBase64(JsonUtils.toJsonString(u));
    }


    private static String encodeBase64(String s) {
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }

    private static String decodeBase64(String base64String) {
        return new String(Base64.getDecoder().decode(base64String));
    }

}
