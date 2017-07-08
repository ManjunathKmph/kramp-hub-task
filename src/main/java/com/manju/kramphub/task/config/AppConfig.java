package com.manju.kramphub.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class holds all the bean definitions for the application.
 * 
 * @author manju
 * @version 1.0
 *
 */
@Configuration
@EnableSwagger2
public class AppConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.manju.kramphub.task.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kramp Hub task with Swagger")
                .description("Kramp Hub task Service Api")
                .version("1.0")
                .build();
    }

}
