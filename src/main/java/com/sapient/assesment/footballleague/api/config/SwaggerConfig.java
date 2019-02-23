package com.sapient.assesment.footballleague.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket serviceDocumentation() {

        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("application-api")
        		.apiInfo(metadata()).select()
        		.apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                
                .build();
    }

   
    private static ApiInfo metadata() {

        return new ApiInfoBuilder().title("Football League")
                .description("Football League ")
                .version("1.0").build();
    }

}