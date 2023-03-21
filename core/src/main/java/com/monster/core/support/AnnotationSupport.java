package com.monster.core.support;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

public final class AnnotationSupport {
    private AnnotationSupport() {
    }

    public static <A extends Annotation> A findAnnotation(Method method, @Nullable Class<A> annotationType) {
        return Objects.isNull(method) ? null : findAnnotation(method, method.getDeclaringClass(), annotationType);
    }

    public static <A extends Annotation> A findAnnotation(Method method, Class<?> clazz, @Nullable Class<A> annotationType) {
        if (Objects.isNull(method)) {
            return null;
        } else {
            A methodAnnotation = AnnotationUtils.findAnnotation(method, annotationType);
            return Objects.isNull(methodAnnotation) ? AnnotationUtils.findAnnotation(clazz, annotationType) : methodAnnotation;
        }
    }
}
