package com.light.blog.core.test.tmp.declare_transaction.propagation.require_new;

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
 * @since 2019/5/23
 */
@Component
public class RequireNewTxDomain {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requireNewTran() {
        jdbcTemplate.update("insert into test values(null,0)");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requireNewTranExp() {
        jdbcTemplate.update("insert into test values(null,0)");
        throw new RuntimeException();
    }

}
