package com.light.blog.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.light.blog.common.vo.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/2/1
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    default IPage<T> selectPage(PageInfo page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        IPage<T> ipage = new Page<>(page.getIndex(), page.getSize());
        return this.selectPage(ipage, queryWrapper);
    }

    default IPage<T> selectPage(PageInfo page) {
        IPage<T> ipage = new Page<>(page.getIndex(), page.getSize());
        return this.selectPage(ipage, null);
    }

    default IPage<T> selectPage(int index, int size, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        IPage<T> ipage = new Page<>(index, size);
        return this.selectPage(ipage, queryWrapper);
    }

}
