package com.light.blog.core.test.tmp.declare_transaction.propagation.nested;

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
public class NestedTxDomain {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NESTED)
    public void nestedTran() {
        jdbcTemplate.update("insert into test values(null,0)");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedTranExp() {
        jdbcTemplate.update("insert into test values(null,0)");
        throw new RuntimeException();
    }

}
