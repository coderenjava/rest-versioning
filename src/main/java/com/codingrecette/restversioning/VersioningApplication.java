package com.codingrecette.restversioning;

import com.codingrecette.restversioning.repository.CommuneRepository;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.codingrecette.restversioning.model.CommuneV1;

import java.time.LocalDate;

@SpringBootApplication
public class VersioningApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersioningApplication.class, args);
	}

	@Bean
	CommuneRepository repository() {
		CommuneRepository repository = new CommuneRepository();
		repository.add(new CommuneV1(44120L, "Vertou", LocalDate.parse("1990-01-20")));
		repository.add(new CommuneV1(44115L, "Basse-Goulaine", LocalDate.parse("1987-01-20")));
		repository.add(new CommuneV1(44980L, "Saint-Luce-Sur-Loire", LocalDate.parse("1982-01-20")));
		return repository;
	}

	@Bean
	public GroupedOpenApi communeApiCustomHeader() {
		return GroupedOpenApi.builder()
				.group("commune-custom-header-example")
				.pathsToMatch("/commune-custom-header/**")
				.build();
	}

	@Bean
	public GroupedOpenApi communeApi10() {
		return GroupedOpenApi.builder()
				.group("commune-api-1.0")
				.pathsToMatch("/commune/v1.0/**")
				.build();
	}

	@Bean
	public GroupedOpenApi communeApi11() {
		return GroupedOpenApi.builder()
				.group("commune-api-1.1")
				.pathsToMatch("/commune/v1.1/**")
				.build();
	}

	@Bean
	public GroupedOpenApi communeApi12() {
		return GroupedOpenApi.builder()
				.group("commune-api-1.2")
				.pathsToMatch("/commune/v1.2/**")
				.build();
	}

//	@Bean
//	public Docket swaggercommuneApi10() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("commune-api-1.0")
//				.select()
//					.apis(RequestHandlerSelectors.basePackage("com.codingrecette.restversioning.controller"))
//					.paths(regex("/commune/v1.0.*"))
//				.build()
//				.apiInfo(new ApiInfoBuilder().version("1.0").title("commune API").description("Documentation commune API v1.0").build());
//	}
//
//	@Bean
//	public Docket swaggercommuneApi11() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("commune-api-1.1")
//				.select()
//					.apis(RequestHandlerSelectors.basePackage("com.codingrecette.restversioning.controller"))
//					.paths(regex("/commune/v1.1.*"))
//				.build()
//				.apiInfo(new ApiInfoBuilder().version("1.1").title("commune API").description("Documentation commune API v1.1").build());
//	}
//
//	@Bean
//	public Docket swaggercommuneApi12() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("commune-api-1.2")
//				.select()
//					.apis(RequestHandlerSelectors.basePackage("com.codingrecette.restversioning.controller"))
//					.paths(regex("/commune/v1.2.*"))
//				.build()
//				.apiInfo(new ApiInfoBuilder().version("1.2").title("commune API").description("Documentation commune API v1.2").build());
//	}
	
}
