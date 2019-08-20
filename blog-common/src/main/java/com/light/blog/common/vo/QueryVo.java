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
    private PageVo pageVo;

    int index = 1;

    int size = 10;

    String keyword;

    //考虑到正常情况下此类不具备可变性,所以设计为非线程安全
    public PageVo getPageVo() {
        if (pageVo == null) {
            pageVo = new PageVo(index, size);
        }
        return pageVo;
    }

    public int getOffset() {
        return (index - 1) * size;
    }

}
