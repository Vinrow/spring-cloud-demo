package com.monster.framework.mybatis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "core.mybatis")
public class MybatisConfig {

    /**
     * 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求 默认false
     */
    @Value("${mybatis-plus.page-config.overflow:false}")
    private boolean overflow;

    /**
     * 设置最大单页限制数量，默认 500 条，-1 不受限制
     */
    @Value("${mybatis-plus.page-config.limit:500}")
    private long limit;

    /**
     * 开启 count 的 join 优化,只针对部分 left join，默认false
     */
    @Value("${mybatis-plus.page-config.optimize-join:false}")
    private boolean optimizeJoin;

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

    public boolean isOptimizeJoin() {
        return optimizeJoin;
    }

    public void setOptimizeJoin(boolean optimizeJoin) {
        this.optimizeJoin = optimizeJoin;
    }

    public MybatisInjectionConfig getInjection() {
        return injection;
    }

    public void setInjection(MybatisInjectionConfig injection) {
        this.injection = injection;
    }

    public static class MybatisInjectionConfig {

        /**
         * 是否启动SQL注入检查，默认开启
         */
        private boolean enabled = true;

        /**
         * SQL注入关键字集合
         */
        private List<String> stopWords =
                Arrays.asList(
                        "drop ",
                        "and ",
                        "or ",
                        "sleep(",
                        "delay ",
                        "benchmark(",
                        "exec ",
                        "grant ",
                        "@@",
                        "/*");
        /**
         * 检测到关键字后是否直接抛出异常，默认为true
         */
        private boolean throwEnabled = true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<String> getStopWords() {
            return stopWords;
        }

        public void setStopWords(List<String> stopWords) {
            this.stopWords = stopWords;
        }

        public boolean isThrowEnabled() {
            return throwEnabled;
        }

        public void setThrowEnabled(boolean throwEnabled) {
            this.throwEnabled = throwEnabled;
        }
    }

}
