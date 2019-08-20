package com.light.blog.common.test.utils;

import com.light.blog.common.utils.ReflectUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/19
 */
public class ReflectUtilsTest {

    static class Foo{
        int a=123;
        Integer b =234;
        Double c = 345.0;
        int [] d = {1,3,4};
        Bar e ;
    }

    static class Bar{

    }


    @Test
    public void getObjPropValue(){

        Foo f = new Foo();
        int r1 = ReflectUtils.getObjPropValue(f,"a");
        Integer r2 = ReflectUtils.getObjPropValue(f,"b");
        Double r3 = ReflectUtils.getObjPropValue(f,"c");
        int [] r4 = ReflectUtils.getObjPropValue(f,"d");
        Bar r5 = ReflectUtils.getObjPropValue(f,"e");
        println(r1,r2,r3,r4,r5);
        Bar r6 = ReflectUtils.getObjPropValue(f,"f");

    }

    static void println(Object...args){
        String text = Arrays.stream(args).reduce((a,b)->{
            return a.toString()+"\n"+b;
        }).get().toString();

        System.out.println(text);
    }

    @Test
    public void f(){
        int i =13;
//        System.out.println(i instanceof int);
    }



}
