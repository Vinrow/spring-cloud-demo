package com.monster.framework.core.exception;

import com.monster.framework.core.errors.IError;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class ServiceException extends BaseException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(IError error) {
        super(error);
    }

    public ServiceException(IError error, @Nullable Object... messageArgs) {
        super(error, messageArgs);
    }

    public ServiceException(IError error, @NonNull Throwable cause, @Nullable Object... messageArgs) {
        super(error, cause, messageArgs);
    }
}
