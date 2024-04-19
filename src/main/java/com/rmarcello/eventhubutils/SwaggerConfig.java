package com.rmarcello.eventhubutils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
	public OpenAPI customOpenAPI() {
		OpenAPI openapi= new OpenAPI()
				.components(new Components())
				.info(new Info().title("EventHubUtils API").version("1.0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
       return openapi;
	}
    
}