package com.light.blog.common.exceptions;


/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2018-10-5
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String format, Object... args) {
        super(String.format(format, args));
    }

    public ApplicationException(Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }


}
