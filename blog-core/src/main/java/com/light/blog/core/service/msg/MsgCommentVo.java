package com.light.blog.core.service.msg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.light.blog.common.vo.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MsgCommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String articleKey;

    private LocalDateTime postDate;

    private String content;

    private Boolean isDeleted;

    private String email;

    private Long refId;

    @ApiModelProperty(hidden = true)
    private Long rootId;

    private String userAgent;

    private String website;

    private Integer upvote;

    private Integer downvote;

    private MsgCommentVo ref;

    private List<MsgCommentVo> replies;

    private PageInfo repliesPageInfo;

    //用于在通知「被回复者 」时,让其可以定位此回复.
    private String replyURL;

    //文章评论总数
    private Long articleCommentTotal;

}
