package com.light.blog.common.utils.http;

import com.light.blog.common.utils.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2018/12/6
 */
public class HttpUtils {


    public static String toFormData(Object object) {
        Map params = BeanUtils.copyAs(object, Map.class);
        return toFormData(params);
    }

    public static String toFormData(Map<String,String> params) {
        StringBuffer sb = new StringBuffer();
        params.forEach(
                (k, v) -> {
                    try {
                        sb.append(URLEncoder.encode(String.valueOf(k),"utf-8"));
                        sb.append("=");
                        sb.append(URLEncoder.encode(String.valueOf(v),"utf-8"));
                        sb.append("&");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
        );
        return sb.toString().replaceAll("&$", "");
    }

    public static String toQueryString(Map params) {
        return "?" + toFormData(params);
    }

    public static String toQueryString(Object params) {
        return "?" + toFormData(params);
    }
}
