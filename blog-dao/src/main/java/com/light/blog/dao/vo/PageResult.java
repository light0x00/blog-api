package com.light.blog.dao.vo;

import com.light.blog.common.vo.PageInfo;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/22
 */
public class PageResult<T> {
    List<T> data;

    PageInfo pageVo;

    public PageResult(List<T> data, PageInfo pageVo) {
        this.data = data;
        this.pageVo = pageVo;
    }

    public static <T> PageResult<T> of(List<T> data, PageInfo pageVo) {
        return new PageResult<>(data, pageVo);
    }

    public static <T> PageResult<T> ofEmpty(PageInfo pageVo) {
        return new PageResult<>(Collections.emptyList(), pageVo);
    }

    public static <T> PageResult<T> ofEmpty() {
        return new PageResult<>(Collections.emptyList(), new PageInfo());
    }


}
