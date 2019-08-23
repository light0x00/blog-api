package com.light.blog.dao.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class MsgComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String articleKey;

    private LocalDateTime postDate;

    private String content;

    private Boolean isDeleted;

    private String email;
/*等需要专门的用户中心时再实现*/
    private Integer upvote;

    private Integer downvote;

    private String userAgent;

    private Long refId;

    private String website;

    private Long rootId;

}
