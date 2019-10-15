package com.everwing.cloud.web.wy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/7
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${security.oauth2.client.accessTokenUri}")
    private String authTokenUri;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.everwing.cloud.api.wy.controller"))
                .paths(PathSelectors.any())
                .build().securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("物业APIs")
                .description("--------------------------------")
                .termsOfServiceUrl("https://oschina.net/shiny")
                .contact("shiny")
                .version("0.1.1")
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(authTokenUri);

        return new OAuthBuilder()
                .name("wy_web")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("wy_web", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("app", "App scope is trusted!")
        };
    }
}
