package br.com.jmsdevelopment.ecom.config.swagger;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.jmsdevelopment.ecom.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .apiInfo(apiInfo());
    }

    private SecurityContext securityContext() {
        SecurityContextBuilder builder = SecurityContext.builder();
        builder.securityReferences(defaultAuth());
        builder.operationSelector(operationContext -> operationContext.requestMappingPattern().contains("/pedido")
        || (operationContext.requestMappingPattern().contains("/cliente") && !operationContext.httpMethod().equals(HttpMethod.POST)));
        return builder
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("Cliente", "Acesso aos endpoints protegidos");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(
                new SecurityReference("Token", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Token", HttpHeaders.AUTHORIZATION, In.HEADER.name());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("E-commerce REST API")
                .description("Api rest que simula as principais funcionalidades de um e-commerce")
                .version("1.0.0")
                .license("GPL v3")
                .contact(new Contact("James Botelho", "https://www.jmsdevelopment.com.br/", "jms.devel@gmail.com"))
                .build();
    }
}
