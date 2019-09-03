package com.light.blog.common.utils.http;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2018/12/1
 */
public class RestTemplateWrapper {

    static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(
                Arrays.asList(new StringHttpMessageConverter(Charset.forName("utf-8")))
        );
    }


    public static <T> ResponseEntity<T> get(String url, List<String> cookie, MultiValueMap<String, String> headers, Class<T> responseClass) {
        return exchange(url, HttpMethod.GET, cookie, headers, null, null, responseClass);
    }

    public static <T> ResponseEntity<T> get(String url, MultiValueMap<String, String> headers, Class<T> responseClass) {
        return exchange(url, HttpMethod.GET, null, headers, null, null, responseClass);
    }

    public static <T> ResponseEntity<T> get(String url, Class<T> responseClass) {
        return exchange(url, HttpMethod.GET, null, null, null, null, responseClass);
    }

    public static <T> ResponseEntity<T> get(String url, Map uriParams, Class<T> responseClass) {
        String fullUrl = url + HttpUtils.toQueryString(uriParams);
        return exchange(fullUrl, HttpMethod.GET, null, null, null, null, responseClass);
    }

    public static <T> ResponseEntity<T> get(String url, Object uriParams, Class<T> responseClass) {
        String fullUrl = url + HttpUtils.toQueryString(uriParams);
        return exchange(fullUrl, HttpMethod.GET, null, null, null, null, responseClass);
    }


    public static <T> ResponseEntity<T> post(String url, Class<T> responseClass) {
        return exchange(url, HttpMethod.POST, null, null, null, null, responseClass);
    }

    public static <T> ResponseEntity<T> post(String url, String body, MultiValueMap<String, String> headers, Class<T> responseClass) {
        return exchange(url, HttpMethod.POST, null, headers, null, body, responseClass);
    }

    public static <T> ResponseEntity<T> post(String url, String body, Class<T> responseClass) {
        return exchange(url, HttpMethod.POST, null, null, null, body, responseClass);
    }

    public static <T> ResponseEntity<T> post(String url, Map params, Class<T> responseClass) {
        return exchange(url, HttpMethod.POST, null, null, null, HttpUtils.toFormData(params), responseClass);
    }

    public static <T> ResponseEntity<T> post(String url, Object params, Class<T> responseClass) {
        return exchange(url, HttpMethod.POST, null, null, null, HttpUtils.toFormData(params), responseClass);
    }

    public static <T> ResponseEntity<T> postJson(String url, Object obj, Class<T> responseClass) {
        return exchange(url, HttpMethod.POST, null, null, null, JSON.toJSONString(obj), responseClass);
    }


    /**
     * @param url
     * @param cookie  可空
     * @param headers 可空
     * @param params  可空
     * @param body    可空
     * @return
     */
    public static <T> ResponseEntity<T> exchange(String url, HttpMethod httpMethod, List<String> cookie, MultiValueMap<String, String> headers, Map<String, Object> params, String body, Class<T> responseClass) {

        HttpHeaders requestHeaders = new HttpHeaders();
        if (headers != null)
            requestHeaders.putAll(headers);
        if (cookie != null)
            requestHeaders.put("Cookie", cookie);
        requestHeaders.put("Content-Type", Arrays.asList("application/x-www-form-urlencoded"));

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, requestHeaders);

        ResponseEntity<T> response;
        if (params != null)
            response = restTemplate.exchange(url, httpMethod, requestEntity, responseClass, params);
        else
            response = restTemplate.exchange(url, httpMethod, requestEntity, responseClass);
        return response;
    }

}
