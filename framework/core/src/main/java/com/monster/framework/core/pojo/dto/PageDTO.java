package com.monster.framework.core.pojo.dto;

import com.monster.framework.core.pojo.entity.SerializableObject;

public class PageDTO extends SerializableObject {
    private Long pageIndex;
    private Long pageSize;
    private String order;
    /**
     * asc desc
     */
    private String sort;

    private Boolean searchCount = true;

    private final Long DEFAULT_PAGE_INDEX = 1L;
    private final Long DEFAULT_PAGE_SIZE = 15L;

    public PageDTO() {
        this.pageIndex = DEFAULT_PAGE_INDEX;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageDTO(Long pageIndex, Long pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public PageDTO(Long pageIndex, Long pageSize, Boolean searchCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.searchCount = searchCount;
    }

    public PageDTO(Long pageIndex, Long pageSize, String order, String sort) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.order = order;
        this.sort = sort;
    }

    public PageDTO(Long pageIndex, Long pageSize, String order, String sort, Boolean searchCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.order = order;
        this.sort = sort;
        this.searchCount = searchCount;
    }

    public Long getPageIndex() {
        return pageIndex;
    }

    public PageDTO setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public PageDTO setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public PageDTO setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public PageDTO setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public PageDTO setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }
}