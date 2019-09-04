package com.light.blog.dao.vo;

import com.light.blog.common.vo.PageInfo;
import com.light.blog.common.vo.QueryVo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
@Getter
@Setter
@Accessors(chain = true)
public class QueryMsgCommentVo extends QueryVo {

    String articleKey;

    PageInfo repliesPageInfo;

    boolean lastPage;


}
