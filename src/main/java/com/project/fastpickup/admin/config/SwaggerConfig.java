package com.project.fastpickup.admin.config;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Swagger Documnet Class
//http://localhost:8080/swagger-ui/index.html#/
@Configuration
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Joonny Swagger", version = "v1"))
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi chatOpenApi() {
    String[] paths = { "/**" };

    return GroupedOpenApi.builder()
        .group("Joon OPEN API v1")
        .pathsToMatch(paths)
        .build();
  }
}