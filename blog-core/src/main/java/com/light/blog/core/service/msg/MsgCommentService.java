package com.light.blog.core.service.msg;

import com.light.blog.common.utils.BeanUtils;
import com.light.blog.common.vo.OutputModel;
import com.light.blog.dao.entities.MsgComment;
import com.light.blog.dao.mapper.MsgCommentMapper;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import com.light.blog.common.utils.Assert;
import com.light.blog.common.vo.PageVo;
import com.light.blog.common.vo.PagingOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.light.blog.common.utils.CheckUtils.gtZero;
import static com.light.blog.common.utils.CheckUtils.isNotEmpty;

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

    public OutputModel<MsgCommentVo> add(MsgCommentVo vo) {
        //check
        Assert.isTrue(isNotEmpty(vo.getArticleKey()), "没有指定文章key");
        Assert.isTrue(isNotEmpty(vo.getContent()), "内容不能为空");

        //adjust
        if (gtZero(vo.getRefId())) {
            Assert.notNull(mcMapper.selectById(vo.getRefId()), "引用的评论不存在");
        } else
            vo.setRefId(-1L);

        vo.setPostDate(LocalDateTime.now());
        vo.setIsDeleted(false);

        //do
        //TODO 检查文章key是否存在

        MsgComment po = BeanUtils.copyAs(vo,MsgComment.class);
        mcMapper.insert(po);

        vo.setId(po.getId());
        return OutputModel.ofSuccess(vo);
    }


    public PagingOutputModel<MsgCommentVo> query(QueryMsgCommentVo in) {
        Assert.notNull(in.getArticleKey(),"没有指定article key");

        //TODO 检查key是否存在

        List<MsgComment> mcs = mcMapper.queryByArticle(in);
        List<MsgCommentVo> mcVos = mcs.stream().map(i -> BeanUtils.copyAs(i, MsgCommentVo.class)).collect(Collectors.toList());

        RefDeterminer determiner = new RefDeterminer(mcVos);
        for (MsgCommentVo mcVo : mcVos) {
            determiner.fuck(mcVo, 1, in.getRefDepth());
        }

        long total = mcMapper.countByArticle(in);
        return PagingOutputModel.ofSuccess(mcVos, new PageVo(in.getIndex(), in.getSize(), total));
    }


    /**
     * 递归找出评论引用. 内置缓存机制,一个RefDeterminer实例对应一次评论的query
     */
    class RefDeterminer {

        Map<Long,MsgCommentVo> cachePool;

        public RefDeterminer(List<MsgCommentVo> cache){
            this.cachePool = cache.stream().collect(Collectors.toMap(
                    MsgCommentVo::getId,i->i
            ));
        }

        private MsgCommentVo getCache(Long id){
            MsgCommentVo target =  cachePool.get(id);
            if(target!=null){
                return target;
            }
            MsgComment mc= mcMapper.selectById(id);
            if(mc==null){
                return null;
            }
            target = BeanUtils.copyAs(mc, MsgCommentVo.class);
            cachePool.put(target.getId(),target);
            return target;
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
                MsgCommentVo ref= getCache(refId);
                Assert.notNull(ref, "数据库数据存在错误,没有找到%s对于的记录");
                mcVo.setRef(ref);
                fuck(ref, ++depth, depthLimit);
            }
        }

    }




}
