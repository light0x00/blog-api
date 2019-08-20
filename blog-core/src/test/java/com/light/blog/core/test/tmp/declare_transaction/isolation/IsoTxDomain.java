package com.light.blog.core.test.tmp.declare_transaction.isolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
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
public class IsoTxDomain {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
        在没有设置propagation的情况下(

        在没有设置propagation为REQUIRES_NEW的情况下, isolation可能会被忽略,
        原因是:
            与外层事务方法共用一个事务的情况下,设置隔离级别的sql外层事务方法就已经设置好了,而内层事务方法没有新开事务的情况下, 必然使用父事务方法的isolation。
       */
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
    public void nestedTran() {
        jdbcTemplate.execute("select * from test");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedTranExp() {
        jdbcTemplate.update("insert into test values(null,0)");
        throw new RuntimeException();
    }

}
