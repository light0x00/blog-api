package com.light.blog.core.test.tmp.declare_transaction.propagation.require;

import org.springframework.beans.factory.annotation.Autowired;
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
public class RequireTxService {

    @Autowired
    RequireTxDomain example_domain;


    /*
        org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only

        1. 首先根据REQUIRE的特性 requireNewTran、requireTranExp会使用requireCaller的transaction, 即三者通过TreadLocal共享同一个transaction
        2. requireTranExp发生了异常,进入exception-advice,标记当前事务为rollback-only状态(注意:是标记状态,而不是直接在子方法执行rollback)
        3. requireCaller由于catch了异常,所以不会进入exception-advice,而是执行完后在after-advice里尝试commit,
        对应的实现代码 org.springframework.transaction.support.AbstractPlatformTransactionManager.commit中 检查了当前事务状态的rollback-only是否被标记为了true,如果为true执行rollback并随后抛出UnexpectedRollbackException

        总结: spring认为 明明子级事务方法抛出了异常 开心的标记当前事务状态为rollback-only=true,
        就等着外层事务方法的exception-advice里rollback了, 结果外层方法catch了异常执行完成后进入after-advice准备commit,
        在commit时spring留了一个心眼检查了一下only-rollback的值, 发现为true,contributor表示你TM在逗我呢,这不抛个UnexpectedRollbackException给你能忍?

        值得一提的是默认情况下 即使方法没有@Transactional修饰,spring也会优先使用当前线程绑定的那个transaction


    */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tranCaller() {
        example_domain.requireTran();
//        try {
            example_domain.requireTranExp();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }

    }

}
