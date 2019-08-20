package com.light.blog.common.utils;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/29
 */
public enum ExpMsg {

    ShopNotFound("没有找到你的商店!");



    public String value;
    ExpMsg(String msg){
        this.value = msg;
    }

    public String getValue(Object...args) {
        return String.format(value,args);
    }

    public String getValue() {
        return value;
    }
}
