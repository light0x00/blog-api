package com.light.blog.web.test;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/23
 */

public class DateFormatTest {

    @Test
    public void t0() {
        LocalDateTime dt = LocalDateTime.ofInstant(Instant.ofEpochMilli(1566529386991L), ZoneId.of("+8"));
        LocalDateTime dt2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(1566529386991L), ZoneId.of("+0"));
        System.out.println(dt);
        System.out.println(dt2);
    }
}
