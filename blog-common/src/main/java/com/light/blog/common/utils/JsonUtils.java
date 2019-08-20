package com.light.blog.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 * @auther: light
 * @since: 2018/5/23 17:54
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper_01;  //不匹配的字段不映射
    private static final ObjectMapper mapper_02;  //不匹配、为空的字段不映射

    static {

        mapper_01 = new ObjectMapper();
        mapper_01.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper_02 = new ObjectMapper();
        mapper_02.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper_02.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public static ObjectMapper objectMapper() {
        return mapper_01;
    }

    /**
     * 排除空字段
     *
     * @return
     */
    public static ObjectMapper objectMapper_02() {
        return mapper_02;
    }


    public static String toJsonString(Object target) {
        try {
            return mapper_01.writeValueAsString(target);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("toJsonString时 发生异常");
        }
    }


    public static <T> T parseJson(String jsonStr, Class<T> clazz) {
        try {
            return mapper_01.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("parseJson 时发生异常");
        }
    }

}
