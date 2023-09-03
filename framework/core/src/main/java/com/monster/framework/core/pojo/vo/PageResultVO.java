package com.monster.framework.core.pojo.vo;

import com.monster.framework.core.pojo.entity.SerializableObject;

import java.util.List;

public class PageResultVO <T> extends SerializableObject {
    private Long total;
    private List<T> rows;
    private Long pageIndex;
    private Long pageSize;

    public PageResultVO() {}

    public PageResultVO(long pageIndex, long pageSize, long total, List<T> rows) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public PageResultVO<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public PageResultVO<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public Long getPageIndex() {
        return pageIndex;
    }

    public PageResultVO<T> setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public PageResultVO<T> setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

}
