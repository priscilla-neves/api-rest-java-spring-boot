package br.com.universidade.gerenciamento;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@PropertySource(value = "classpath:/swagger.properties")
@OpenAPIDefinition(
	info = @Info(
		title = "Gerenciamento Universitário API",
		version = "v1",
		description = "This app provides REST APIs gerenciamento entities",
		contact = @Contact(
			name = "PRISCILLA NEVES DE SOUZA SANTOS",
			email = "priscilla.neves@outlook.com",
			url = "https://github.com/priscilla-neves/api-rest-java-spring-boot"
		)
	)
)
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI(@Value("v1") String appVersion) {
		return new OpenAPI()
			.components(
				new Components().addSecuritySchemes(
					"bearer-jwt",
					new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
						.in(SecurityScheme.In.HEADER)
						.name("Authorization")
				)
			)
			.addSecurityItem(
				new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"))
			);
	}
}
