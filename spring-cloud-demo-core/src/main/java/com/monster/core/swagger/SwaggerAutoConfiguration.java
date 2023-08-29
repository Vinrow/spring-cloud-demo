package com.monster.core.swagger;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@ConditionalOnProperty(prefix = "uem.swagger", value = {"enable"}, havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
@Configuration(proxyBeanMethods = false)
public class SwaggerAutoConfiguration {

    private static final String HEADER_COMPANY_ID = "companyId";

    private static ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact(properties.getAuthor(), null, null))
                .version(properties.getVersion())
                .build();
    }

    private static List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header"));
    }

    private static List<SecurityReference> securityReferences() {
        return Collections.singletonList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes()));
    }

    private static AuthorizationScope[] authorizationScopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")};
    }

    private static List<Parameter> globalRequestParameters() {
        List<Parameter> tenantParameter = new ArrayList<>();
        tenantParameter.add(new ParameterBuilder()
                .name(HEADER_COMPANY_ID)
                .description("租户编号")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(true)
                .build());
        return tenantParameter;
    }

    private static Predicate<String> getAuthExcludeReg(SwaggerProperties properties) {
        String[] exclude = Optional.ofNullable(properties)
                .map(p -> p.getAuthExclude())
                .map(p -> p.split(StrUtil.COMMA)).orElse(new String[0]);
        if (ArrayUtil.isEmpty(exclude)) {
            return PathSelectors.any();
        }
        List<String> path = Arrays.stream(exclude).map(e -> e.replaceFirst(StrUtil.SLASH, StrUtil.EMPTY)).collect(Collectors.toList());
        String combinedStrPAth = String.join("|", path);
        String regStr = StrUtil.format("^(?!\\/(?:{})).*$", combinedStrPAth);
        return PathSelectors.regex(regStr);
    }

    private static List<SecurityContext> securityContexts(SwaggerProperties properties) {
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(securityReferences())
                .forPaths(getAuthExcludeReg(properties))
                .build());
    }

    @Bean
    public Docket createRestApi(SwaggerProperties properties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(properties))
                .select()
                .apis(basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts(properties))
                .globalOperationParameters(globalRequestParameters());
    }
}
