package com.light.blog.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @auther: light
 * @since: 2018/10/5 15:40
 * <p>
 * 不需要分页的list 应当使用 Output<List<T>>
 * </p>
 */
@Data
public class PagingOutputModel<T> extends OutputModel<List<T>> {

    private PageInfo pageInfo;

    public PagingOutputModel(List<T> data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
        setCode(DefaultSuccess.getCode());
        setMsg(DefaultSuccess.getMsg());
    }

    public static <A> PagingOutputModel<A> ofSuccess(List<A> datas, PageInfo pageInfo) {
        return new PagingOutputModel<>(datas, pageInfo);
    }

    public static <A> PagingOutputModel<A> ofEmpty() {
        return new PagingOutputModel<>(Collections.emptyList(), new PageInfo());
    }

    public static <A> PagingOutputModel<A> ofSuccess(List<A> datas, long index, long size, long total) {
        return new PagingOutputModel<>(datas, new PageInfo(index, size, total));
    }

    public static <A> PagingOutputModel<A> ofSuccess(IPage<A> page) {
        return new PagingOutputModel<>(page.getRecords(), new PageInfo(page.getCurrent(), page.getSize(), page.getTotal()));
    }

}
