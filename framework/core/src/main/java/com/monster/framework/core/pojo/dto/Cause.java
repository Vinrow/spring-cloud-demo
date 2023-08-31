package com.monster.framework.core.pojo.dto;

import com.monster.framework.core.pojo.entity.SerializableObject;


public class Cause extends SerializableObject {

    /**
     * 请求路径
     */
    private String path;

    /**
     * 错误信息
     */
    private String message;

    public String getPath() {
        return path;
    }

    /**
     * 当前时间
     */
    private Long timestamp;

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Cause{" +
                "path='" + path + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
