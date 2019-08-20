package com.light.blog.dao.mapper;

import com.light.blog.dao.MyBaseMapper;
import com.light.blog.dao.entities.MsgComment;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author light
 * @since 2019-08-19
 */
public interface MsgCommentMapper extends MyBaseMapper<MsgComment> {

    List<MsgComment> queryByArticle(QueryMsgCommentVo queryArticleVo);

    @Select("select count(1) from msg_comment where article_key=#{articleKey}")
    long countByArticle(QueryMsgCommentVo queryArticleVo);
}
