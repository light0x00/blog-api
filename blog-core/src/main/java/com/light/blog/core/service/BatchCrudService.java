package com.light.blog.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.light.blog.dao.MyBaseMapper;
import com.light.blog.common.utils.ReflectUtils;
import com.light.blog.common.vo.OutputModel;
import com.light.blog.common.vo.PageVo;
import com.light.blog.common.vo.PagingOutputModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/5/22
 */
public class BatchCrudService<M extends MyBaseMapper<E>, E> extends ServiceImpl<M, E> {

    @Autowired
    protected M mapper;

    public OutputModel<E> baseCreateOrUpdate(E entity) {
        int r;
        if (ReflectUtils.getObjPropValue(entity, "id") == null) {
            r = mapper.insert(entity);
        } else {
            r = mapper.updateById(entity);
        }
        return r > 0 ? OutputModel.ofSuccess(entity) : OutputModel.ofWarn();
    }

    public OutputModel baseDeleteById(int id) {
        int i = mapper.deleteById(id);
        return i > 0 ? OutputModel.ofSuccess() : OutputModel.ofWarn("删除失败!");
    }

    public OutputModel<E> baseGetById(int id) {
        E e = mapper.selectById(id);
        return e != null ? OutputModel.ofSuccess(e) : OutputModel.ofWarn();
    }

    public PagingOutputModel<E> baseQuery(PageVo page) {
        IPage<E> r = mapper.selectPage(page, null);
        return PagingOutputModel.ofSuccess(r);
    }

}
