package com.monster.core.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Objects;

public final class JsonSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSupport.class);
    private static ObjectMapper objectMapper;

    private JsonSupport() {
    }

    public static void init(ObjectMapper objectMapper) {
        JsonSupport.objectMapper = objectMapper;
    }

    public static <T> T string2Object(String value, Class<T> clazz) {
        if (StringUtils.hasText(value)) {
            try {
                return objectMapper.readValue(value, clazz);
            } catch (JsonProcessingException ex) {
                LOGGER.error("failed to serialize object to string", ex);
            }
        }
        return null;
    }

    public static <T> T string2Object(String value, TypeReference<T> typeReference) {
        if (StringUtils.hasText(value)) {
            try {
                return objectMapper.readValue(value, typeReference);
            } catch (JsonProcessingException ex) {
                LOGGER.error("failed to serialize object to string", ex);
            }
        }
        return null;
    }

    public static String object2String(Object value) {
        if (Objects.nonNull(value)) {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException ex) {
                LOGGER.error("failed to deserialize object to string", ex);
            }
        }
        return null;
    }
}
