package com.light.blog.web.config.aop;


import com.light.blog.common.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * @auther: light
 * @since: 2018/10/6 17:47
 * <p></p>
 */
@Slf4j
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {


    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        long timstamp;
        try {
            timstamp = Long.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ApplicationException("时间戳格式不正确%s", text);
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timstamp), ZoneId.of("0")); //转utc
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object.toString();
    }
}
