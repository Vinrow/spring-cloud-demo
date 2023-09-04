package com.monster.framework.mybatis.service;

import com.monster.framework.core.pojo.dto.PageDTO;
import com.monster.framework.core.pojo.vo.PageResultVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<T> {

    List<T> list();

    long count();

    List<T> list(T entity);

    long count(T entity);

    PageResultVO<T> list(PageDTO pageDto, T entity);

    T getById(Serializable id);

    boolean save(T entity);

    T saveEntity(T entity);

    boolean saveBatch(Collection<T> entityList);

    boolean saveBatch(Collection<T> entityList, int batchSize);

    Collection<T> saveBatchEntity(Collection<T> entityList);

    Collection<T> saveBatchEntity(Collection<T> entityList, int batchSize);

    boolean updateById(T entity);

    boolean removeById(Serializable id);

    boolean remove(Collection<? extends Serializable> ids);

    boolean remove(T entity);

}