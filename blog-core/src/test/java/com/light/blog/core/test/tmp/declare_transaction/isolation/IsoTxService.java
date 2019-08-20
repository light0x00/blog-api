package com.light.blog.core.test.tmp.declare_transaction.isolation;

import com.light.blog.core.test.CoreTestApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreTestApplication.class)
@Component
public class IsoTxService {

    @Autowired
    IsoTxDomain nestedTxDomain;

    /*
        在没有设置propagation设置为非REQUIRES_NEW的情况下（即与外层事务方法公用一个事务）, isolation可能会被忽略,
        原因是:
            与外层事务方法共用一个事务的情况下,设置隔离级别的sql外层事务方法就已经设置好了,而内层事务方法没有新开事务的情况下, 必然使用父事务方法的isolation。
    */
    @Transactional
    public void tranCaller(){
        nestedTxDomain.nestedTran();
    }
}
