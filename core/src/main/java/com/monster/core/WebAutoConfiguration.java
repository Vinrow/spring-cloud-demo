package com.monster.core;

import com.monster.core.config.ApiResponseConfig;
import com.monster.core.pojo.Dto.ResponseDto;
import com.monster.core.support.I18nSupport;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(ApiResponseConfig.class)
@ComponentScan(basePackageClasses = {WebAutoConfiguration.class})
public class WebAutoConfiguration {

    private String baseName;
    private final MessageSource messageSource;

    private final ApiResponseConfig apiResponseConfig;

    public WebAutoConfiguration(MessageSource messageSource, ApiResponseConfig apiResponseConfig) {
        this.messageSource = messageSource;
        this.apiResponseConfig = apiResponseConfig;
    }

    @PostConstruct
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void init() {
        Set<String> baseNames = new LinkedHashSet<>();
        if (StringUtils.hasText(baseName)) {
            baseNames.addAll(StringUtils.commaDelimitedListToSet(StringUtils.trimAllWhitespace(baseName)));
            if (messageSource instanceof AbstractResourceBasedMessageSource) {
                baseNames.addAll(((AbstractResourceBasedMessageSource) messageSource).getBasenameSet());
                ((AbstractResourceBasedMessageSource) messageSource).setBasenames(baseNames.toArray(new String[0]));
            }
            ResponseDto.init(apiResponseConfig.getResultConfig().getSuccessCode(), apiResponseConfig.getResultConfig().getSuccessMessage());
            I18nSupport.init(messageSource);
        }
    }
}
