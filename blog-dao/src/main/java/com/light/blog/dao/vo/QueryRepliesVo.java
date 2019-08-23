package com.light.blog.dao.vo;

import com.light.blog.common.vo.QueryVo;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/22
 */
@Data
public class QueryRepliesVo extends QueryVo {

    //根评论id
    private Long rootId;

}
