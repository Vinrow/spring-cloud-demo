package com.monster.framework.core.exception;

import com.monster.framework.core.errors.IError;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class ApiException extends BaseException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(IError errorCode) {
        super(errorCode);
    }

    public ApiException(IError errorCode, @Nullable Object... messageArgs) {
        super(errorCode, messageArgs);
    }

    public ApiException(IError errorCode, @NonNull Throwable cause, @Nullable Object... messageArgs) {
        super(errorCode, cause, messageArgs);
    }
}
