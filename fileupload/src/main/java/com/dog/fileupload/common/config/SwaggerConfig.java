package com.dog.fileupload.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public ModelResolver modelResolver(ObjectMapper objectMapper) {
		return new ModelResolver(objectMapper);
	}

	private static final String SECURITY_SCHEME_NAME = "authorization";

	@Bean
	public OpenAPI swaggerApi() {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
					.name(SECURITY_SCHEME_NAME)
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")))
			.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
			.info(new Info()
				.title("Springdoc 테스트")
				.description("Springdoc을 사용한 Swagger UI 테스트")
				.version("1.0.0"));
	}

	@Bean
	public OperationCustomizer globalHeader() {
		return (operation, handlerMethod) -> {
			operation.addParametersItem(new Parameter()
				.in(ParameterIn.HEADER.toString())
				.schema(new StringSchema().name("Refresh-Token"))
				.name("Refresh-Token"));
			return operation;
		};
	}
}

