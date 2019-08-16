package com.mipo.common.page;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private long total; //总记录数
    private List<T> list; //结果集
    private int pageNum; //第几页
    private int pageSize; //每页记录数
    private int pages; // 总页数
    private int size; //当前页的数量<=pageSize

    public PageBean(List<T> list){
        if (list instanceof Page){
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
        }
    }
}
