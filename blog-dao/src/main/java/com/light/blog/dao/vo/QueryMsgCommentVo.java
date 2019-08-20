package com.light.blog.dao.vo;

import com.light.blog.common.vo.QueryVo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
@Data
@Accessors(chain = true)
public class QueryMsgCommentVo extends QueryVo {

    String articleKey;

    int refDepth = 5;

}
