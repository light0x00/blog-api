package com.light.blog.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther: light
 * @since: 2018/10/7 18:53
 * <p>
 * S   service
 * M   mapper
 * E   entity
 * </p>
 * <p>
 *     把这些没有任何校验的数据库操作暴露在公网是一件很危险的事
 * </p>
 */
public abstract class BaseController<S> {

    @Autowired
    protected S service;

}
