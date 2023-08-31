package com.monster.framework.core.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monster.framework.core.errors.IError;
import com.monster.framework.core.pojo.entity.SerializableObject;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> extends SerializableObject {
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

    public ResponseDTO() {
    }

    public static void init(String code, String message) {
        ResponseDTO.SUCCESS_CODE = code;
        ResponseDTO.SUCCESS_MESSAGE = message;
    }

    public static <T> ResponseDTO<T> success(T data) {
        return success(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ResponseDTO<T> success(String code, String msg, T data) {
        return new ResponseDTO<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static <T> ResponseDTO<T> fail(IError error) {
        return fail(error.getCode(), error.getMessage());
    }

    public static <T> ResponseDTO<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    private static <T> ResponseDTO<T> fail(String code, String message, Cause cause) {
        return new ResponseDTO<T>().setCode(code).setMsg(message).setCause(cause);
    }

    public String getCode() {
        return code;
    }

    public ResponseDTO<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseDTO<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Cause getCause() {
        return cause;
    }

    public ResponseDTO<T> setCause(Cause cause) {
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
