package com.light.blog.web.config.aop;

import com.light.blog.common.exceptions.BusinessException;
import com.light.blog.common.vo.OutputModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: light
 * @since: 2018/6/4 16:27
 * <p></p>
 */
@RestControllerAdvice
public class ExceptionCatcher {

    private static Logger logger = LoggerFactory.getLogger(ExceptionCatcher.class);

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Object exception(Exception e) {
        logger.error("捕获到异常", e);
        return OutputModel.ofError("应用程序内部异常", e);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Object validException(HttpServletRequest request, Exception e) {
        logger.error("捕获到异常", e);
        return OutputModel.ofWarn("入参不合法");
    }


    @ExceptionHandler({ BusinessException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OutputModel l(Exception e) {
        logger.error("捕获到异常", e);
        return OutputModel.ofWarn(e.getMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Object validException(HttpRequestMethodNotSupportedException e) {
        logger.error("捕获到异常", e);
        return OutputModel.ofWarn("入参不合法");
    }

}
