package com.monster.framework.mybatis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "mybatis-plus")
public class MybatisConfig {

    /**
     * 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求 默认false
     */
    @Value("${page-config.overflow:false}")
    private boolean overflow;

    /**
     * 设置最大单页限制数量，默认 500 条，-1 不受限制
     */
    @Value("${page-config.limit:500}")
    private long limit;

    /**
     * SQL注入配置
     */
    @NestedConfigurationProperty
    private MybatisInjectionConfig injection = new MybatisInjectionConfig();

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public MybatisInjectionConfig getInjection() {
        return injection;
    }

    public void setInjection(MybatisInjectionConfig injection) {
        this.injection = injection;
    }
}
