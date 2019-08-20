package com.light.blog.core.test.tmp.declare_transaction.propagation.require;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/22
 */
@Component
public class RequireTxDomain {

    @Autowired
    JdbcTemplate jdbcTemplate;


//    @Transactional(propagation = Propagation.REQUIRED)
    public void requireTran() {
        jdbcTemplate.update("insert into test values(null,0)");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void requireTranExp() {
        jdbcTemplate.update("insert into test values(null,0)");
        throw new RuntimeException();
    }
}
