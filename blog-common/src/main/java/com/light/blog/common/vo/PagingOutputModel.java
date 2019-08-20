package com.light.blog.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

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

    private PageVo pageInfo;

    public PagingOutputModel(List<T> data, PageVo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
        setCode(DefaultSuccess.getCode());
        setMsg(DefaultSuccess.getMsg());
    }

    public static <A> PagingOutputModel<A> ofSuccess(List<A> datas, PageVo pageInfo) {
        return new PagingOutputModel<>(datas, pageInfo);
    }

    public static <A> PagingOutputModel<A> ofSuccess(List<A> datas, long index, long size, long total) {
        return new PagingOutputModel<>(datas, new PageVo(index, size, total));
    }

    public static <A> PagingOutputModel<A> ofSuccess(IPage<A> page) {
        return new PagingOutputModel<>(page.getRecords(), new PageVo(page.getCurrent(), page.getSize(), page.getTotal()));
    }

}
