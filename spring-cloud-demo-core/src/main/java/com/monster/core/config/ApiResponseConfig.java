package com.monster.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "api")
public class ApiResponseConfig {

    @NestedConfigurationProperty
    private ResultConfig resultConfig = new ResultConfig();

    @NestedConfigurationProperty
    private List<CorsConfig> corsConfigs = Arrays.asList(new CorsConfig("/api/**"));

    public ResultConfig getResultConfig() {
        return resultConfig;
    }

    public void setResultConfig(ResultConfig resultConfig) {
        this.resultConfig = resultConfig;
    }

    public List<CorsConfig> getCorsConfigs() {
        return corsConfigs;
    }

    public void setCorsConfigs(List<CorsConfig> corsConfigs) {
        this.corsConfigs = corsConfigs;
    }
}
