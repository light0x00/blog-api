package com.light.blog.common.vo;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/19
 */
public enum ResponseStatus {
    Success(0,"success"),Warn(100,"warn"),Error(200,"error"),NotLogin(101,"未登录"),NoAccess(102,"无权限");

    int code;
    String msg;

    ResponseStatus(int code, String msg){
        this.code=code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
