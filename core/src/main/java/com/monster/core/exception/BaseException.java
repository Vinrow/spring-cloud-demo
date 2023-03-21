package com.monster.core.exception;

import com.monster.core.errors.IError;

public abstract class BaseException extends RuntimeException {
    private transient IError error;
    private Object[] messageArgs;

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String massage, Throwable cause) {
        super(massage, cause);
    }

    protected BaseException(IError error) {
        this.error=error;
    }

    protected BaseException(IError error, Object... messageArgs) {
        this(error, (Throwable) null, messageArgs);
    }

    protected BaseException(IError error, Throwable cause, Object... messageArgs) {
        super(error.getCode(), cause);
        this.error = error;
        this.messageArgs = messageArgs;
    }

    public boolean isI18nSupport(){
        return true;
    }

    public IError getError() {
        return this.error;
    }

    public Object[] getMessageArgs() {
        return this.messageArgs;
    }
}
