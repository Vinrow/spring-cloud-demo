package com.monster.core.config;

import org.springframework.lang.Nullable;

public class CorsConfig {
    private String pathPattern;

    private String[] allowedOrigins;

    private String[] allowedMethods;

    private String[] allowedHeaders;

    private String[] exposedHeaders;

    private Boolean allowedCredentials;

    private Long maxAge;

    public CorsConfig() {
    }

    public CorsConfig(@Nullable String pathPattern) {
        this.pathPattern = pathPattern;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public void setPathPattern(String pathPattern) {
        this.pathPattern = pathPattern;
    }

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String[] allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public String[] getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(String[] allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public String[] getExposedHeaders() {
        return exposedHeaders;
    }

    public void setExposedHeaders(String[] exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }

    public Boolean getAllowedCredentials() {
        return allowedCredentials;
    }

    public void setAllowedCredentials(Boolean allowedCredentials) {
        this.allowedCredentials = allowedCredentials;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }
}
