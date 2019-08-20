package com.light.blog.common.test.utils;

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
}
