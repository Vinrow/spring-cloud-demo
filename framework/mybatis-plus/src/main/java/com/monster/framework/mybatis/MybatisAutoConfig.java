package com.monster.framework.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.monster.framework.mybatis.config.MybatisConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {MybatisAutoConfig.class})
@EnableConfigurationProperties(MybatisConfig.class)
public class MybatisAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            value = "mybatis-plus.page-config.enabled",
            havingValue = "true",
            matchIfMissing = true)
    public PaginationInnerInterceptor paginationInterceptor(MybatisConfig mybatisConfig) {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        paginationInterceptor.setOverflow(mybatisConfig.isOverflow());
        paginationInterceptor.setMaxLimit(mybatisConfig.getLimit());
        return paginationInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            value = "mybatis-plus.global-config.db-config.oracle-seq-enabled",
            havingValue = "true")
    public IKeyGenerator oracleKeyGenerator() {
        return new OracleKeyGenerator();
    }
}
