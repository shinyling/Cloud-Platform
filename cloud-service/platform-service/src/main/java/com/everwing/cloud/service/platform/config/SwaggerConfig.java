package com.everwing.cloud.service.platform.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author DELL shiny
 * @create 2020/2/24
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String serviceName;

    private String serviceDesc = "授权中心";

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;

    public static final String securitySchemaOAuth2 = "oauth2schema";

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(serviceName).description(serviceDesc).build();
    }


    private Predicate<String> postPaths() {
        return regex("/.*");
    }

    /**
     * 这里使用 ResourceOwnerPasswordCredentialsGrant 也就是password授权方式
     *
     * @return
     */
    private OAuth securitySchema() {
        //这里设置 client 的 scope
        final AuthorizationScope authorizationScope = new AuthorizationScope("app", "允许测试阶段访问的所有接口");
        final GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);
        return new OAuth(securitySchemaOAuth2, Arrays.asList(authorizationScope), Arrays.asList(grantType));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(this.defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(clientId, clientSecret, "", "", "access_token", ApiKeyVehicle.QUERY_PARAM, "access_token", " ");
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope =
                new AuthorizationScope("app", "允许测试阶段访问的所有接口");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference(securitySchemaOAuth2, authorizationScopes));
    }

}