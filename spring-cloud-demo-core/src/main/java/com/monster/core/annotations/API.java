package com.monster.core.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@RestController
@RequestMapping
@APIResponseBody
public @interface API {
    @AliasFor(
            annotation = RestController.class,
            attribute = "value"
    )
    String bean() default "";

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] value() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] path() default {};

    String tag() default "";

    String description() default "";

}
