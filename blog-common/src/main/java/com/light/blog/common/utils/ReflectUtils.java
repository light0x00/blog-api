package com.light.blog.common.utils;


import com.light.blog.common.exceptions.BusinessException;

import java.lang.reflect.Field;

/**
 * <p>
 *      此类已废
 *
 *      已经有了更好的替代品:
 *          https://github.com/jOOQ/jOOR
 * </p>
 *
 * @author light
 * @since 2018/11/23
 */
public class ReflectUtils {


    /**
     * 获取对象的指定字段值
     *
     * @param obj        对象
     * @param propName       字段
     * @param <T>   决定返回值的类型
     * @return
     */
    public static <T> T getObjPropValue(Object obj, String propName) {

        T val = null;

        Class<?> findClass = obj.getClass();
        try {
            while (findClass != Object.class) {
                try {
                    //1. 找目标字段
                    Field f = findClass.getDeclaredField(propName);
                    f.setAccessible(true);
                    Object testVal = f.get(obj);

                    //4. 找到了则返回该字段的值
                    val = (T) testVal;
                    break;
                }
                //2.1 找不到字段时 循环继续, 除非 supperClass 已经是 Object
                catch (NoSuchFieldException e) {
                    if (findClass.getSuperclass() == Object.class) {
                        throw new BusinessException(e, String.format("没有在对象(%s)中找到指定的字段(%s)", obj, propName));
                    }
                }
                //2.2 其他异常直接抛出 终止循环
                catch (IllegalAccessException e) {
                    throw e;
                }
                //3. 去父类找
                findClass = obj.getClass().getSuperclass();
            }
        } catch (IllegalAccessException e) {
            throw new BusinessException(e, String.format("访问对象(%s)的字段(%s)时发生错误", obj, propName));
        }
        return val;
    }

}
