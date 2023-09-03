package com.monster.framework.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.monster.framework.core.context.UserContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConditionalOnProperty(
        value = "mybatis.column.auto-fill",
        havingValue = "true",
        matchIfMissing = true)
@Component
public class ColumnAutoFillHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColumnAutoFillHandler.class);

    @Value("${mybatis.column.create-at:createdDate}")
    private String createAt;

    @Value("${mybatis.column.create-by:createdBy}")
    private String createBy;

    @Value("${mybatis.column.last-modified-at:lastModifiedAt}")
    private String lastModifiedAt;

    @Value("${mybatis.column.last-modified-by:lastModifiedBy}")
    private String lastModifiedBy;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        String userId = UserContextHolder.getUserAccount();
        LOGGER.debug("insertFill now:{}, userId:{}", now, userId);
        this.strictInsertFill(metaObject, createAt, Date.class, now);
        this.strictInsertFill(metaObject, lastModifiedAt, Date.class, now);

        this.strictInsertFill(metaObject, createBy, String.class, userId);
        this.strictInsertFill(metaObject, lastModifiedBy, String.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        String userId = UserContextHolder.getUserAccount();
        LOGGER.debug("updateFill now:{}, userId:{}", now, userId);
        this.strictUpdateFill(metaObject, lastModifiedAt, Date.class, now);
        this.strictUpdateFill(metaObject, lastModifiedBy, String.class, userId);
    }
}
