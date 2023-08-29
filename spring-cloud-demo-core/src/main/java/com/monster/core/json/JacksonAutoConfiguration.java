package com.monster.core.json;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Arrays;
import java.util.List;

@Configuration(proxyBeanMethods = false)
public class JacksonAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(SerializationFeature.WRAP_ROOT_VALUE, SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return builder;
    }

    @Bean
    @ConditionalOnBean(name = {"jackson2ObjectMapperBuilder"})
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        List<Module> modules = Arrays.asList(new JavaTimeModule(), new SimpleModule());
        return builder.createXmlMapper(false).modules(modules).build();
    }
}
