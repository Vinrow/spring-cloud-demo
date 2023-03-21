package com.monster.core.response;

import com.monster.core.annotations.API;
import com.monster.core.errors.IError;
import com.monster.core.pojo.Dto.Cause;
import com.monster.core.pojo.Dto.ResponseDto;
import com.monster.core.support.RequestSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice(annotations = {API.class})
public class ExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
    public static final int ORDER = 100;

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handlException(Exception ex) {

    }

    protected ResponseDto<String> buildErrorResult(@NonNull IError error,@NonNull Exception ex){
        Objects.requireNonNull(error);
        Objects.requireNonNull(ex);
        String errorCode = Optional.ofNullable(error.getCode()).orElse("");
        I1
    }

    protected Cause buildCause(Exception ex) {
        try {
            Cause cause = new Cause();
            cause.setMessage(getErrorMessage(ex));
            String requestPath = Optional.ofNullable(RequestSupport.getRequest().getRequestURI()).orElse("");
            cause.setPath(requestPath);
            cause.setTimestamp(System.currentTimeMillis());
            return cause;
        } catch (Exception e) {
            LOGGER.error("build cause error", e);
        }
        return null;
    }

    private String getErrorMessage(Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getClass().getName())
                .append(":")
                .append(Objects.nonNull(ex.getCause()) ? ex.getCause().getMessage() : ex.getMessage());
        for (StackTraceElement traceElement : ex.getStackTrace()) {
            sb.append("\t").append("at").append(traceElement);
        }
        return sb.toString();
    }
}
