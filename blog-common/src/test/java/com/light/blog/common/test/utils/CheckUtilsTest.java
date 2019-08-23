package com.light.blog.common.test.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.light.blog.common.utils.Assert;
import com.light.blog.common.utils.CheckUtils;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/30
 */
public class CheckUtilsTest {

    @Test
    public void allGtZero() {

        Assert.isTrue(
                !CheckUtils.allGtZero(1, 2, null)
        );

        Assert.isTrue(
                !CheckUtils.allGtZero(1, 2, 0)
        );

        Assert.isTrue(
                CheckUtils.allGtZero(1, 2, 2)
        );
    }

    @Test
    public void anyLtZeroOrNil() {

        Assert.isTrue(
                CheckUtils.anyLtZeroOrNil(1, 2, null)
        );
        Assert.isTrue(
                !CheckUtils.anyLtZeroOrNil(1, 2, 3)
        );

    }

    @Test
    public void allGteZero(){
        Assert.isTrue(
                CheckUtils.allGteZero(1,2,0)
        );

        Assert.isTrue(
                !CheckUtils.allGteZero(1,2,null)
        );

        Assert.isTrue(
                !CheckUtils.allGteZero(1,2,-1)
        );
    }

    @Test
    public void allEq(){
        Assert.isTrue(
                CheckUtils.allEq(new Integer[]{1,1},1)
        );

        Assert.isTrue(
                !CheckUtils.allEq(new Integer[]{2,2,1},1)
        );
    }

    @Test
    public void anyEq(){
        Assert.isTrue(
                CheckUtils.anyEq(new Integer[]{2,2,1},1)
        );
        Assert.isTrue(
                !CheckUtils.anyEq(new Integer[]{2,2,2},1)
        );
    }

    @Test
    public void allNotNil(){
        Assert.isTrue(
                CheckUtils.allNotNil(1,2,3,new Integer(1))
        );
        Assert.isTrue(
                !CheckUtils.allNotNil(1,null,3,new Integer(1))
        );
    }

    @Test
    public void allNil(){
        Assert.isTrue(
                !CheckUtils.allNil(1,null,3,new Integer(1))
        );

        Assert.isTrue(
                CheckUtils.allNil(null,null,null)
        );
    }
}
