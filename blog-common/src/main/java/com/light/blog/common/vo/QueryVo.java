package com.light.blog.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/20
 */
@Data
@Accessors(chain = true)
public class QueryVo {

    @ApiModelProperty(hidden = true)
    private PageInfo pageInfo;

    long index = 1;

    long size = 10;

    String keyword;

    //考虑到正常情况下此类不具备可变性,所以设计为非线程安全
    public PageInfo getPageInfo() {
        if (pageInfo == null)
            pageInfo = new PageInfo(index, size);
        return pageInfo;
    }

    public PageInfo newPageInfo(long total) {
        return new PageInfo(index, size, total);
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.index = pageInfo.getIndex();
        this.size = pageInfo.getSize();
        this.pageInfo = pageInfo;
    }

    public long getOffset() {
        return (index - 1) * size;
    }

    public void setSize(long size) {
        if (size > 0)
            this.size = size;
    }

    public void toLastIndex(long total) {
        index = total % size == 0 ? total / size : total / size + 1;
    }

}
