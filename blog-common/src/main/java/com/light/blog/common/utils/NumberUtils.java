package com.light.blog.common.utils;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/10
 */
public class NumberUtils {

    /**
     * 丢掉精度意外的位数
     *
     * @param num      小数
     * @param accuracy 保留精度
     * @return
     */
    public static double floor(double num, int accuracy) {
        double a = Math.pow(10, accuracy);
        return ((int) (num * a)) / a;
    }


    /**
     * 如果
     *
     * @param num
     * @param accuracy
     * @return
     */
    public static double ceil(double num, int accuracy) {

        double floorNum = floor(num,accuracy);
        boolean needPlus = num > floorNum;

        if(needPlus) {
            double plusNum = 1 / Math.pow(10, accuracy);
            System.out.println(plusNum+","+floorNum);
            //处理 二进制对小数的影响
            return BigDecimal.valueOf(floorNum).add(BigDecimal.valueOf(plusNum)).doubleValue();
        }else {
         return floorNum;
        }

    }
}
