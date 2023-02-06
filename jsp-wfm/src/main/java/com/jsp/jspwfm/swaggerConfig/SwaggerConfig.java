package com.jsp.jspwfm.swaggerConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
	@Bean
	public OpenAPI baseOpenApi() {
		return new OpenAPI().info(new Info().title("Spring Doc").version("1.0.0").description("Spring Doc"));
	}
}
