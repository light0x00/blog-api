package com.light.blog.common.test.utils;

import com.light.blog.common.utils.Assert;
import com.light.blog.common.utils.NumberUtils;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/10
 */
public class NumberUtilsTest {

    @Test
    public void floorTest(){
        Assert.isTrue(NumberUtils.floor(1.234,2)==1.23);
    }

    @Test
    public void ceilTest(){
        double r = NumberUtils.ceil(0.120001,1);
        double r2 = NumberUtils.ceil(0.120000,1);
        System.out.println(r);
        System.out.println(r2);
    }

    @Test
    public void ceilTest2(){
        double r = NumberUtils.ceil(67.39999999999999,1);
        System.out.println(r);

    }
}
