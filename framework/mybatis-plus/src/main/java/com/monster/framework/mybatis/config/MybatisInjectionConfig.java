package com.monster.framework.mybatis.config;

import java.util.Arrays;
import java.util.List;

public class MybatisInjectionConfig {

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
