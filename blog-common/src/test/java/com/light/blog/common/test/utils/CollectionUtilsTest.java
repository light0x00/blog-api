package com.light.blog.common.test.utils;

import com.light.blog.common.utils.CollectionUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/7
 */
public class CollectionUtilsTest {

    @Test
    public void testDifference(){
        List<Integer > lstA = Arrays.asList(1,2,3,4);
        List<Integer > lstB = Arrays.asList(1,0,3,4);

        List<Integer> lstDiff = CollectionUtils.difference(lstA,lstB);
        System.out.println(lstDiff);

        List<Integer> lstDiff2 = CollectionUtils.difference(lstB,lstA);
        System.out.println(lstDiff2);
    }
}
