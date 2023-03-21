package com.monster.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class I18nSupport {
    public static final Object[] EMPTY_ARGS = new String[0];
    private static final Logger LOGGER = LoggerFactory.getLogger(I18nSupport.class);
    private static MessageSource messageSource;

    private I18nSupport() {
    }

    public static void init(MessageSource messageSource) {
        I18nSupport.messageSource = messageSource;
    }

    public static String getMessage(String code, Object... args) {
        Assert.hasText(code, "code must not be null");
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, String defaultMessage, Object... args) {
        try {
            return getMessage(code, args);
        } catch (Exception ex) {
            LOGGER.error("failed to get i18n message by key #{}", code, ex);
        }
        return defaultMessage;
    }
}
