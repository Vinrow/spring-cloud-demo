package com.monster.framework.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.framework.core.pojo.dto.PageDTO;
import com.monster.framework.core.pojo.vo.PageResultVO;
import com.monster.framework.mybatis.service.BaseService;
import com.monster.framework.mybatis.utils.PageUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    public static final int IS_DELETE_ENABLE = 0;

    public static final int IS_DELETE_DISABLE = 1;

    public static final String SORT_ASC = "asc";

    public static final String SORT_DESC = "desc";

    @Override
    public List<T> list() {
        return super.list();
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public List<T> list(T entity) {
        QueryWrapper<T> wrapper = new QueryWrapper<>(entity);
        return super.list(wrapper);
    }

    @Override
    public long count(T entity) {
        QueryWrapper<T> wrapper = new QueryWrapper<>(entity);
        return super.count(wrapper);
    }

    @Override
    public PageResultVO<T> list(PageDTO pageDto, T entity) {
        QueryWrapper<T> wrapper = new QueryWrapper<>(entity);
        Page<T> page = getPage(pageDto);
        if (StringUtils.hasText(pageDto.getOrder())) {
            OrderItem orderItem;
            if (SORT_DESC.equals(pageDto.getSort())) {
                orderItem = OrderItem.desc(pageDto.getOrder());
            } else {
                orderItem = OrderItem.asc(pageDto.getOrder());
            }
            page.addOrder(orderItem);
        }
        IPage<T> result = super.page(page, wrapper);
        return PageUtils.buildPage(result);
    }

    @Override
    public T getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(T entity) {
        return super.save(entity);
    }

    @Override
    public T saveEntity(T entity) {
        super.save(entity);
        return entity;
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public Collection<T> saveBatchEntity(Collection<T> entityList) {
        super.saveBatch(entityList);
        return entityList;
    }

    @Override
    public Collection<T> saveBatchEntity(Collection<T> entityList, int batchSize) {
        super.saveBatch(entityList, batchSize);
        return entityList;
    }

    @Override
    public boolean updateById(T entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean remove(Collection<? extends Serializable> ids) {
        if (ids == null || ids.size() == 0) {
            return false;
        }
        return super.removeByIds(ids);
    }

    @Override
    public boolean remove(T entity) {
        if (entity == null) {
            return false;
        }
        QueryWrapper<T> wrapper = new QueryWrapper<>(entity);
        return super.remove(wrapper);
    }

    protected <E> Page<E> getPage(PageDTO pageDTO) {
        return new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize(), pageDTO.getSearchCount());
    }
}
