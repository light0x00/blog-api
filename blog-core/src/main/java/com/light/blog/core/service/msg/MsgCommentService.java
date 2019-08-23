package com.light.blog.core.service.msg;

import com.light.blog.common.utils.BeanUtils;
import com.light.blog.common.utils.CheckUtils;
import com.light.blog.common.vo.OutputModel;
import com.light.blog.dao.entities.MsgComment;
import com.light.blog.dao.mapper.MsgCommentMapper;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import com.light.blog.common.utils.Assert;
import com.light.blog.common.vo.PageInfo;
import com.light.blog.common.vo.PagingOutputModel;
import com.light.blog.dao.vo.QueryRepliesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.light.blog.common.utils.CheckUtils.*;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
@Service
public class MsgCommentService {

    @Autowired
    MsgCommentMapper mcMapper;

    /**
     * 添加一条评论。
     * <p>
     * refId为空或小于0，都视为根评论.
     * <p>
     * 返回包含ref对象的vo
     *
     * @param vo
     * @return
     */
    public OutputModel<MsgCommentVo> add(MsgCommentVo vo) {
        //check
        Assert.isTrue(isNotEmpty(vo.getArticleKey()), "没有指定文章key");
        Assert.isTrue(isNotEmpty(vo.getContent()), "内容不能为空");
        Assert.isTrue(isNotEmpty(vo.getNickname()), "昵称不能为空");
        Assert.isTrue(isNotEmpty(vo.getEmail()), "邮箱不能为空");

        //TODO 检查文章key是否存在
        MsgComment ref = null;
        //adjust
        if (lteZeroOrNil(vo.getRefId())) {
            vo.setRefId(-1L);
            vo.setRootId(-1L);
        } else {
            ref = mcMapper.selectById(vo.getRefId());
            Assert.notNull(ref, "引用的评论(refId=%s)不存在", vo.getRefId());
            vo.setRootId(ref.getRootId() == -1 ? ref.getId() : ref.getRootId());
        }
        vo.setPostDate(LocalDateTime.now());
        vo.setIsDeleted(false);

        MsgComment po = BeanUtils.copyAs(vo, MsgComment.class);
        mcMapper.insert(po);
        vo.setId(po.getId());
        if (ref != null)
            vo.setRef(BeanUtils.copyAs(ref, MsgCommentVo.class));
        return OutputModel.ofSuccess(vo);
    }


    public PagingOutputModel<MsgCommentVo> queryRoot(QueryMsgCommentVo in) {
        //check
        Assert.notNull(in.getArticleKey(), "没有指定article key");
        //TODO 检查key是否存在
        //adjust
        if (in.getRepliesPageInfo() == null)
            in.setRepliesPageInfo(new PageInfo());

        //分页按顶级评论
        long total = mcMapper.countRoot(in);
        if (total == 0)
            return PagingOutputModel.ofSuccess(Collections.emptyList(), in.newPageInfo(total));
        if (in.isLastPage())
            in.toLastIndex(total);

        //查顶级评论
        List<MsgComment> pos = mcMapper.queryRoots(in);
        List<MsgCommentVo> vos = BeanUtils.copyAsArrayList(pos, MsgCommentVo.class);

        //查回复
        for (MsgCommentVo vo : vos) {
            QueryRepliesVo q = new QueryRepliesVo();
            q.setRootId(vo.getId());
            q.setPageInfo(in.getRepliesPageInfo());

            PagingOutputModel<MsgCommentVo> model = queryReplies(q);
            vo.setReplies(model.getData());
            vo.setRepliesPageInfo(model.getPageInfo());
        }

        return PagingOutputModel.ofSuccess(vos, new PageInfo(in.getIndex(), in.getSize(), total));
    }


    public PagingOutputModel<MsgCommentVo> queryReplies(QueryRepliesVo in) {
        Assert.isTrue(CheckUtils.gtZero(in.getRootId()), "没有指定rootId");
        //raw
        long total = mcMapper.countReplies(in);
        if (total == 0)
            return PagingOutputModel.ofEmpty();

        List<MsgComment> pos = mcMapper.queryReplies(in);
        List<MsgCommentVo> vos = BeanUtils.copyAsArrayList(pos, MsgCommentVo.class);

        //determine ref
        RefDeterminer refDeterminer = new RefDeterminer(vos);
        for (MsgCommentVo vo : vos) {
            refDeterminer.determine(vo);
        }
        return PagingOutputModel.ofSuccess(vos, in.newPageInfo(total));
    }

    /** ----------------------------------------------------- 内部工具 ----------------------------------------------------- **/

    /**
     * 递归找出评论引用. 内置缓存机制,一个RefDeterminer实例对应一次评论的query
     */
    class RefDeterminer {

        Map<Long, MsgCommentVo> cachePool;

        public RefDeterminer(List<MsgCommentVo> cache) {
            this.cachePool = cache.stream().collect(Collectors.toMap(
                    MsgCommentVo::getId, i -> i
            ));
        }

        private MsgCommentVo getCache(Long id) {
            MsgCommentVo target = cachePool.get(id);
            if (target != null) {
                return target;
            }
            MsgComment mc = mcMapper.selectById(id);
            if (mc == null) {
                return null;
            }
            target = BeanUtils.copyAs(mc, MsgCommentVo.class);
            cachePool.put(target.getId(), target);
            return target;
        }

        public void determine(MsgCommentVo vo) {
            fuck(vo, 1, 1);
        }

        public void recursiveDetermine(MsgCommentVo vo, int depthLimit) {
            fuck(vo, 1, depthLimit);
        }

        /**
         * @param mcVo
         * @param depth      当前递归深度
         * @param depthLimit 正数表示不限制递归层级
         */
        public void fuck(MsgCommentVo mcVo, int depth, int depthLimit) {
            if (depthLimit > 0 && depth > depthLimit) {
                return;
            }
            if (gtZero(mcVo.getRefId())) {
                long refId = mcVo.getRefId();
                MsgCommentVo ref = getCache(refId);
                Assert.notNull(ref, "数据库数据存在错误,没有找到%s对于的记录");
                mcVo.setRef(ref);
                fuck(ref, ++depth, depthLimit);
            }
        }

    }


}
