package com.light.blog.core.test.tmp.declare_transaction;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/23
 */

import com.light.blog.core.test.CoreTestApplication;
import com.light.blog.core.test.tmp.declare_transaction.isolation.IsoTxService;
import com.light.blog.core.test.tmp.declare_transaction.propagation.nested.NestedTxService;
import com.light.blog.core.test.tmp.declare_transaction.propagation.require.RequireTxService;
import com.light.blog.core.test.tmp.declare_transaction.propagation.require_new.RequireNewTxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreTestApplication.class)
@Component
public class DeclareTxTest {

    @Autowired
    NestedTxService nestedTxService;
    @Autowired
    RequireTxService requireTxService;
    @Autowired
    RequireNewTxService requireNewTxService;

    @Autowired
    IsoTxService isoTxService;

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Before
//    public void before() {
//        jdbcTemplate.execute("truncate table gc.tmp");
//    }

    @Test
    public void requireNew(){
        requireNewTxService.tranCaller();
    }

    @Test
    public void require(){
        requireTxService.tranCaller();
    }

    @Test
    public void nested(){
        nestedTxService.tranCaller();
    }


    @Test
    public void nestedIso(){
        isoTxService.tranCaller();
    }

}
