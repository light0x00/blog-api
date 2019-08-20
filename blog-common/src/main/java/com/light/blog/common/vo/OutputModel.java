package com.light.blog.common.vo;

import lombok.Data;

/**
 * @auther: light
 * @since: 2018/10/5 15:40
 * <p>
 * 定义:
 * <p>
 * warn  业务异常
 * error 应用bug
 * success 正常
 * <p>
 * 对于code,一般情况使用默认的,如果有需要前端特殊对待的(比如未登录时 前端需要禁止用户的动作）则在 {@link ResponseStatus } 定义
 *
 * </p>
 */
@Data
public class OutputModel<T> {

    protected static ResponseStatus DefaultError = ResponseStatus.Error;
    protected static ResponseStatus DefaultWarn = ResponseStatus.Warn;
    protected static ResponseStatus DefaultSuccess = ResponseStatus.Success;

    protected T data;
    protected String msg;
    protected int code;
    protected Throwable exp;

    /* -------------------------------- warn -------------------------------- */


    public static OutputModel ofWarn() {
        return ofWarn(DefaultWarn.getCode(), DefaultWarn.getMsg());
    }

    public static OutputModel ofWarn(String msg) {
        return ofWarn(DefaultWarn.getCode(), msg);
    }

    public static OutputModel ofWarn(ResponseStatus responseStatus) {
        return ofWarn(responseStatus.getCode(), responseStatus.getMsg());
    }

    public static OutputModel ofWarn(int code, String msg) {
        OutputModel resultMode = new OutputModel<>();
        resultMode.setCode(code);
        resultMode.setMsg(msg != null ? msg : DefaultWarn.msg);
        return resultMode;
    }



    /* -------------------------------- error -------------------------------- */

    public static OutputModel ofError(String msg, Throwable th) {
        OutputModel resultMode = new OutputModel<>();
        resultMode.setCode(DefaultError.getCode());
        resultMode.setMsg(msg != null ? msg : DefaultWarn.getMsg());
        if (th != null)
            resultMode.setExp(th);
        return resultMode;
    }

    public static OutputModel ofError() {
        return ofError(null, null);
    }

    public static OutputModel ofError(String msg) {
        return ofError(msg, null);
    }

    /* -------------------------------- success -------------------------------- */

    public static OutputModel ofSuccess() {
        return ofSuccess(DefaultSuccess.code,DefaultSuccess.msg);
    }

    public static <T> OutputModel<T> ofSuccess(T data) {
        return ofSuccess(data, DefaultSuccess.msg);
    }

    public static <T> OutputModel<T> ofSuccess(T data, String msg) {
        OutputModel<T> resultMode = new OutputModel<>();
        resultMode.setData(data);
        resultMode.setCode(DefaultSuccess.getCode());
        resultMode.setMsg(msg == null ? DefaultSuccess.getMsg() : msg);
        return resultMode;
    }

}
