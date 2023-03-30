package com.monster.core.pojo.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monster.core.errors.IError;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static String SUCCESS_CODE = "0";

    private static String SUCCESS_MESSAGE = "ok";

    /**
     * 错误码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 成功响应数据
     */
    private T data;

    private Cause cause;

    public ResponseDto() {
    }

    public static void init(String code, String message) {
        ResponseDto.SUCCESS_CODE = code;
        ResponseDto.SUCCESS_MESSAGE = message;
    }

    public static <T> ResponseDto<T> success(T data) {
        return success(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ResponseDto<T> success(String code, String msg, T data) {
        return new ResponseDto<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static <T> ResponseDto<T> fail(IError error) {
        return fail(error.getCode(), error.getMessage());
    }

    public static <T> ResponseDto<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    private static <T> ResponseDto<T> fail(String code, String message, Cause cause) {
        return new ResponseDto<T>().setCode(code).setMsg(message).setCause(cause);
    }

    public String getCode() {
        return code;
    }

    public ResponseDto<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseDto<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Cause getCause() {
        return cause;
    }

    public ResponseDto<T> setCause(Cause cause) {
        this.cause = cause;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", cause=" + cause +
                '}';
    }
}
