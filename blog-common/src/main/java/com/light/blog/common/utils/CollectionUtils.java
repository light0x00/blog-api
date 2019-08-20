package com.light.blog.common.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/6/7
 */
public class CollectionUtils {


    /**
     * 集合A-集合B的差集
     * @param listA
     * @param listB
     * @param <T>
     * @return
     */
    public static <T>List<T> difference(List<T> listA, List<T>listB){
        List<T> listDiff = new LinkedList<>();
        for (T a : listA) {
            boolean hasA = false;
            for (T b : listB) {
                if(b==a){
                    hasA=true;
                    break;
                }
            }
            if(!hasA){
                listDiff.add(a);
            }
        }
        return listDiff;

    }
}
