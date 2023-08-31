package com.monster.framework.core.errors;


import org.springframework.http.HttpStatus;

public enum DefaultError implements IError {
    INTERNAL_SERVER_ERROR("internalServerError", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("badRequest", HttpStatus.BAD_REQUEST),
    NOT_ACCEPTABLE("notAcceptable",HttpStatus.NOT_ACCEPTABLE),
    METHOD_NOT_ALLOWED("methodNotAllowed",HttpStatus.METHOD_NOT_ALLOWED),
    NOT_FOUND("notFound",HttpStatus.NOT_FOUND),
    ;
    private final String code;
    private final HttpStatus httpStatus;

    DefaultError(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public int getHttpStatus() {
        return this.httpStatus.value();
    }
}
