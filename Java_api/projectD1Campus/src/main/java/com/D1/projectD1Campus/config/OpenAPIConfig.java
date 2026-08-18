package com.D1.projectD1Campus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI CustomOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API manejo de produtos")
                        .version("1.0.0")
                        .description("API diseñada para el manejo de inverntario")
                );
    }
}
