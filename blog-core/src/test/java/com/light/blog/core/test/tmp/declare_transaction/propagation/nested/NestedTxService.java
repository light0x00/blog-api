package com.light.blog.core.test.tmp.declare_transaction.propagation.nested;

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
public class NestedTxService {

    @Autowired
    NestedTxDomain nestedTxDomain;

    /*
        Nested内部基于savepoint实现, 可以看作每一个自事务方法第一次操作数据库前会savepoint v1(当前版本),
        发生一次时exception-advice里rollback to savepoint v1, 顺利执行完在after-advice里什么都不需要做,
        而最外层事务方法里如果顺利执行完就会commit,反之rollback;
    */
    @Transactional
    public void tranCaller(){
        nestedTxDomain.nestedTran();
        try {
            nestedTxDomain.nestedTranExp();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
