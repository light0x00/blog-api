package com.light.blog.web.config.aop;


import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @auther: light
 * @since: 2018/10/6 17:47
 * <p></p>
 */
@Slf4j
public class DateFormatter implements Formatter<Date> {


    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(object);
    }

}
