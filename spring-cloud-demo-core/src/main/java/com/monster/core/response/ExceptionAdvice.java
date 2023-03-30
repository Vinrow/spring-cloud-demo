package com.monster.core.response;

import com.monster.core.annotations.API;
import com.monster.core.errors.DefaultError;
import com.monster.core.errors.IError;
import com.monster.core.exception.BaseException;
import com.monster.core.pojo.Dto.Cause;
import com.monster.core.pojo.Dto.ResponseDto;
import com.monster.core.support.I18nSupport;
import com.monster.core.support.RequestSupport;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice(annotations = {API.class})
public class ExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
    public static final int ORDER = 100;

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return this.logAndResponseError(DefaultError.INTERNAL_SERVER_ERROR, ex);
    }

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handleException(BaseException ex) {
        return this.logAndResponseError(Objects.isNull(ex.getError()) ? DefaultError.INTERNAL_SERVER_ERROR : ex.getError(), ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
        ResponseEntity<Object> response = this.logAndResponseError(DefaultError.BAD_REQUEST, ex);
        String msg = ex.getBindingResult().getAllErrors().stream()
                .map(error -> I18nSupport.getMessage(error.getDefaultMessage(), new Object[0]))
                .collect(Collectors.joining(";"));
        ((ResponseDto<?>) Objects.requireNonNull(response.getBody())).setMsg(msg);
        return response;
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleException(BindException ex) {
        ResponseEntity<Object> response = this.logAndResponseError(DefaultError.BAD_REQUEST, ex);
        String msg = ex.getBindingResult().getAllErrors().stream()
                .map(error -> I18nSupport.getMessage(error.getDefaultMessage(), new Object[0]))
                .collect(Collectors.joining(";"));
        ((ResponseDto<?>) Objects.requireNonNull(response.getBody())).setMsg(msg);
        return response;
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleException(ConstraintViolationException ex) {
        ResponseEntity<Object> response = this.logAndResponseError(DefaultError.BAD_REQUEST, ex);
        String msg = ex.getConstraintViolations().stream()
                .map(error -> I18nSupport.getMessage(error.getMessage(), error.getExecutableParameters()))
                .collect(Collectors.joining(";"));
        ((ResponseDto<?>) Objects.requireNonNull(response.getBody())).setMsg(msg);
        return response;
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleException(NoHandlerFoundException ex) {
        return this.logAndResponseError(DefaultError.NOT_FOUND, ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(TypeMismatchException.class)
    protected ResponseEntity<Object> handleException(TypeMismatchException ex) {
        return this.logAndResponseError(DefaultError.BAD_REQUEST, ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleException(MissingServletRequestParameterException ex) {
        return this.logAndResponseError(DefaultError.BAD_REQUEST, ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(NoSuchMethodException.class)
    protected ResponseEntity<Object> handleException(NoSuchMethodException ex) {
        return this.logAndResponseError(DefaultError.METHOD_NOT_ALLOWED, ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleException(HttpRequestMethodNotSupportedException ex) {
        return this.logAndResponseError(DefaultError.METHOD_NOT_ALLOWED, ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeException.class)
    protected ResponseEntity<Object> handleException(HttpMediaTypeException ex) {
        return this.logAndResponseError(DefaultError.NOT_ACCEPTABLE, ex);
    }

    @Order(ORDER)
    @ResponseBody
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<Object> handleException(HttpMessageConversionException ex) {
        return this.logAndResponseError(DefaultError.BAD_REQUEST, ex);
    }

    protected ResponseEntity<Object> logAndResponseError(IError error, Exception ex) {
        ResponseDto<String> responseDto = ex instanceof BaseException ? this.buildErrorResult((BaseException) ex) : this.buildErrorResult(error, ex);
        LOGGER.error("an error occurred \t error code: {} \t error message: {} \t httpStatus: {}", responseDto.getCode(), responseDto.getMsg(), error.getHttpStatus(), ex);
        return ResponseEntity.status(error.getHttpStatus()).body(responseDto);
    }

    protected ResponseDto<String> buildErrorResult(BaseException ex) {
        Objects.requireNonNull(ex);
        ResponseDto<String> responseDto = new ResponseDto<>();
        IError error = ex.getError();
        if (Objects.isNull(error)) {
            this.processResponseWithNullError(responseDto, ex.getMessage());
        } else if (ex.isI18nSupport()) {
            this.processResponseWithI8nEnabled(error, responseDto, ex.getMessageArgs());
        } else if (DefaultError.INTERNAL_SERVER_ERROR != error) {
            this.processResponseExceptInternalServerError(error, responseDto, ex.getMessage());
        } else {
            this.processOtherError(responseDto, ex.getMessage());
        }
        responseDto.setCause(this.buildCause(ex));
        return responseDto;
    }

    private <T> void processResponseWithNullError(ResponseDto<T> responseDto, String errorMessage) {
        responseDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .setMsg(errorMessage);
    }

    private <T> void processResponseWithI8nEnabled(IError error, ResponseDto<T> responseDto, Object... args) {
        responseDto.setCode(error.getCode())
                .setMsg(I18nSupport.getMessage(error.getCode(), args));
    }

    private <T> void processResponseExceptInternalServerError(IError error, ResponseDto<T> responseDto, String errorMessage) {
        responseDto.setCode(error.getCode())
                .setMsg(StringUtils.hasText(error.getMessage()) ? error.getMessage() : errorMessage);
    }

    private <T> void processOtherError(ResponseDto<T> responseDto, String errorMessage) {
        responseDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .setMsg(errorMessage);
    }

    protected ResponseDto<String> buildErrorResult(@NonNull IError error, @NonNull Exception ex) {
        Objects.requireNonNull(error);
        Objects.requireNonNull(ex);
        String errorCode = Optional.ofNullable(error.getCode()).orElse("");
        String errorMessage = I18nSupport.getMessage(errorCode, errorCode, I18nSupport.EMPTY_ARGS);
        return ResponseDto.fail(errorCode, errorMessage);
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
            return null;
        }
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
