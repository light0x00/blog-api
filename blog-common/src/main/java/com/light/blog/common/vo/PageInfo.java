package com.light.blog.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;

/**
 * <p>
 *     全称 original page
 * </p>
 * @auther: light
 * @since: 2018/10/6 12:11
 */
@Getter
public class PageInfo {

    protected long index;

    protected long size;

    protected long total;


    public PageInfo() {
        this.size = 10;
        this.index = 1;
    }

    public PageInfo(long index, long size) {
        this.index = index;
        this.size = size;
    }

    public PageInfo(IPage page){
        this.index = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
    }

    public PageInfo(long index, long size, long total) {
        this.index = index;
        this.size = size;
        this.total = total;

    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setIndex(long index) {
        this.index = index;

    }

    public void setTotal(long total) {
        this.total = total;

    }




}
