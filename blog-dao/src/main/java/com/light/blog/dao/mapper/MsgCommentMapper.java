package com.light.blog.dao.mapper;

import com.light.blog.dao.MyBaseMapper;
import com.light.blog.dao.entities.MsgComment;
import com.light.blog.dao.vo.PageResult;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import com.light.blog.dao.vo.QueryRepliesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author light
 * @since 2019-08-19
 */
@Mapper
public interface MsgCommentMapper extends MyBaseMapper<MsgComment> {

    List<MsgComment> queryRoots(QueryMsgCommentVo in);

    @Select("select count(1) from msg_comment where article_key=#{articleKey} and ref_id=-1")
    long countRoot(QueryMsgCommentVo in);

    @Select("select count(1) from msg_comment where article_key=#{articleKey}")
    long countByArticle(String articleKey);

    List<MsgComment> queryReplies(QueryRepliesVo in);

    @Select("select count(1) from msg_comment where root_id=#{rootId}")
    long countReplies(QueryRepliesVo in);


}
