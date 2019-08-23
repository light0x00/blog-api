package com.light.blog.web.controller.msg;

import com.light.blog.common.vo.OutputModel;
import com.light.blog.common.vo.PagingOutputModel;
import com.light.blog.core.service.msg.MsgCommentService;
import com.light.blog.core.service.msg.MsgCommentVo;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import com.light.blog.dao.vo.QueryRepliesVo;
import com.light.blog.web.config.toolkit.GuestApi;
import com.light.blog.web.controller.common.BaseController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
@RestController
@RequestMapping("/mc")
@GuestApi
public class MsgCommentController extends BaseController<MsgCommentService> {

    @PostMapping("/add")
    public OutputModel<MsgCommentVo> add(@ModelAttribute MsgCommentVo in){
        return service.add(in);
    }

    @PostMapping("/query")
    public PagingOutputModel<MsgCommentVo> query(@RequestBody QueryMsgCommentVo in){
        return service.queryRoot(in);
    }

    @PostMapping("/queryReplies")
    public PagingOutputModel<MsgCommentVo> queryReplies(@RequestBody QueryRepliesVo in){
        return service.queryReplies(in);
    }

}
