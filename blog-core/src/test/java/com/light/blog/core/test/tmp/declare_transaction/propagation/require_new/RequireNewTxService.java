package com.light.blog.core.test.tmp.declare_transaction.propagation.require_new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class RequireNewTxService {

    @Autowired
    RequireNewTxDomain requireNewTxDomain;

    /*声明为RequireNew的事务方法不会尝试从ThreadLocal获得已经存在的transaction,而是每次都新建一个独立transaction(应该也要获得单独的connection*/
    @Transactional
    public void tranCaller(){
        requireNewTxDomain.requireNewTran();
        try {
            requireNewTxDomain.requireNewTranExp();
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
