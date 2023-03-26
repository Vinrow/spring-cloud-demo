package com.monster.core.response;

import com.monster.core.annotations.API;
import com.monster.core.annotations.APIResponseBody;
import com.monster.core.pojo.Dto.ResponseDto;
import com.monster.core.support.AnnotationSupport;
import com.monster.core.support.JsonSupport;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

@RestControllerAdvice(annotations = {API.class})
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    public ResponseAdvice() {
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (checkSkipped(returnType)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        Class clazz = this.getReturnTypeClass(returnType.getGenericParameterType());
        if (Objects.nonNull(clazz)) {
            return !ResponseDto.class.isAssignableFrom(clazz) && !ResponseEntity.class.isAssignableFrom(clazz);
        }
        return true;
    }

    @SuppressWarnings("rawtypes")
    private Class getReturnTypeClass(Type returnType) {
        if (returnType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) returnType;
            returnType = parameterizedType.getRawType();
        }
        if (returnType instanceof Class) {
            return (Class) returnType;
        }
        return null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object result = ResponseDto.success(body);
        Class<?> clazz = Objects.isNull(body) ? returnType.getParameterType() : body.getClass();
        if (String.class == clazz) {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return JsonSupport.object2String(result);
        }
        return result;
    }

    private boolean checkSkipped(MethodParameter returnType) {
        APIResponseBody apiResponseBody = AnnotationSupport.findAnnotation(returnType.getMethod(), APIResponseBody.class);
        return Objects.nonNull(apiResponseBody) && !apiResponseBody.value();
    }

}
