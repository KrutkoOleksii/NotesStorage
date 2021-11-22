package com.example.notesStorage.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//   http://localhost:9999/swagger-ui/index.html#/
@Configuration
@EnableOpenApi
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.example.notesStorage.swagger"))
//                .paths(PathSelectors.ant("/api/*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "NOTES STORAGE REST API",
                "Executed command number 2",
                "API NOTES STORAGE",
                "https://app-storagenote.herokuapp.com ",
                new Contact("Aleksei Krutko \n,Andrey Shalygin \n,Виталий Ткачук\n,Slyvka Yevhen\n", "https://app-storagenote.herokuapp.com", "myeaddress@company.com\n"),
                "\nLicense of API", "https://www.apache.org/licences/LICENSE-2.0", Collections.emptyList());
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("CLIENT_ID")
                .clientSecret("CLIENT_SECRET_PASSWORD")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }


//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())
//                .select()
//                `.apis(RequestHandlerSelectors.basePackage("com.example.notesStorage.swagger"))`
//                .paths(PathSelectors.ant("/foos/*"))
////                .paths(PathSelectors.any())
//                .build();


//    private ApiInfo getApiInfo() {
//        return new ApiInfoBuilder()
//                .title("NotesStorage")
//                .description("Custom description")
//                .version("1.0.0")
//                .license("Apache 2.0")
//                .license("https://www.apache.org/licences/LICENSE-2.0")
//                .build();
//    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint("https://app-storagenote.herokuapp.com(AUTH_SERVER)" + "/token", "oauthtoken"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint("https://app-storagenote.herokuapp.com(AUTH_SERVER)" + "/authorize", "CLIENT_ID", "CLIENT_SECRET"))
                .build();

        return new OAuthBuilder().name("spring_oauth")
                .grantTypes(List.of(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("foo", "Access foo API") };
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/foos.*"))
                .build();
    }
}
