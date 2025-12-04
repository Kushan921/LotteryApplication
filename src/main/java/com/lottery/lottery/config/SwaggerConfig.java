package com.lottery.lottery.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/api/v1/**") // scan only API controllers
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenApi() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("Lottery Application API Documentation")
                        .version("1.0")
                        .description("Public APIs for Lottery Application"));
    }
}
