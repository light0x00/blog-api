package com.light.blog.common.test.utils;

import com.light.blog.common.utils.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/3
 */
public class BeanUtilsTest {

    @AllArgsConstructor
    @Data
    class Vo1 {
        String name;
    }
    @AllArgsConstructor
    @Data
    class Vo2 {
        String name;
        String gender;
    }

    @Test
    public void t(){

        Vo1 source = new Vo1("Bob");
        Vo2 target = new Vo2("Jack","male");

        Vo2 r = BeanUtils.copyTo(source,target);
        System.out.println(r);
    }

}
