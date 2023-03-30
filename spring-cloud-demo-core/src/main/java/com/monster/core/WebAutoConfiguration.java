package com.monster.core;

import com.monster.core.config.ApiResponseConfig;
import com.monster.core.pojo.Dto.ResponseDto;
import com.monster.core.support.I18nSupport;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


@Configuration
@EnableConfigurationProperties(ApiResponseConfig.class)
@ComponentScan(basePackageClasses = {WebAutoConfiguration.class})
public class WebAutoConfiguration {

    private final MessageSource messageSource;

    private final ApiResponseConfig apiResponseConfig;

    public WebAutoConfiguration(MessageSource messageSource, ApiResponseConfig apiResponseConfig) {
        this.messageSource = messageSource;
        this.apiResponseConfig = apiResponseConfig;
    }

    @PostConstruct
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void init() {
        ResponseDto.init(apiResponseConfig.getResultConfig().getSuccessCode(), apiResponseConfig.getResultConfig().getSuccessMessage());
        I18nSupport.init(messageSource);
    }
}
