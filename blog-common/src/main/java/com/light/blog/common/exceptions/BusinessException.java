package com.light.blog.common.exceptions;


/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2018-10-5
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String format, Object... args) {
        super(String.format(format, args));
    }

    public BusinessException(Throwable cause,String format, Object... args) {
        super(String.format(format, args), cause);
    }


}
