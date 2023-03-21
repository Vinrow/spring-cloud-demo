package com.monster.core.errors;

@FunctionalInterface
public interface IError {
    String getCode();

    default String getMessage() {
        return "";
    }

    default int getHttpStatus() {
        return 500;
    }
}
