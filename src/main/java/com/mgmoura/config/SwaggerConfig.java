package com.mgmoura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenApi() {

		return new OpenAPI().components(new Components()).info(
				new Info()
					.title("API para controle de consultório médico")
					.description("API desenvolvida com Spring Boot e Spring Data")
					.version("v 1.0")
				);
	}

}
