package com.light.blog.common.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.light.blog.common.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
/**
 * @auther: light
 * @since: 2018/10/8 16:00
 * <p></p>
 */
public class BeanUtils {

    private static Mapper beanMapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 将对象拷贝为指定类型的对象
     *
     * @param source
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> T copyAs(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return beanMapper.map(source, destinationClass);
    }

    public static <T> T copyAs(Object source, Class<T> destinationClass,Function<T,T> whenReturn) {
        T r = copyAs(source,destinationClass);
        return whenReturn.apply(r);
    }


    /**
     * 将集合集合拷贝为指定类型的 集合
     *
     * @param source       输入集合
     * @param destClass 输出集合类型
     * @param destItemClass   输出集合元素的类类型
     * @param <C>
     * @param <I>
     * @return
     */
    public static <C extends Collection<I>, I> C copyAs(Collection source, Class<C> destClass, Class<I> destItemClass) {
        if (source == null) {
            return null;
        }
        C collection = null;
        try {
            collection = destClass.newInstance(); //TODO 可以改为拿 constructor(int) 来初始化集合size 避免add带来的resize开销

            for (Object o : source) {
                collection.add(copyAs(o, destItemClass));
            }

        } catch (InstantiationException | IllegalAccessException e) {
            throw new ApplicationException(e,"dozer拷贝时发生异常!");
        }
        return collection;
    }

    /**
     *
     * @param source 输入集合
     * @param dest 输出集合对象
     * @param destItemClass 输出集合元素类型
     * @param <C>
     * @param <I>
     * @return
     */
    public static <C extends Collection<I>, I> C copyAsCollection(Collection source, C dest,Class<I> destItemClass) {

        if (source == null) {
            return null;
        }

        for (Object o : source) {
            dest.add(copyAs(o, destItemClass));
        }
        return dest;
    }


    public static <T> ArrayList<T> copyAsArrayList(Collection origin, Class<T> itemClass) {
        return copyAsCollection(origin,new ArrayList<>(origin.size()), itemClass);
    }

}
