package com.light.blog.web.controller.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/9/2
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MusicVo {

    String id;
    String name;
    String artist;
    String cover;
    String url;

}
