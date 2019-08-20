package com.light.blog.core.domain;

import com.light.blog.core.utils.UserPrincipalContext;
import com.light.blog.dao.entities.UserVo;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/21
 */
@Component
public class UserDomain {

    public boolean isAdministrator(){
        UserVo u = UserPrincipalContext.getRequird();
        return false;
    }

}
