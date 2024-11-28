package com.example.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("institute_service", r -> r.path("/institute/**")
						.uri("http://localhost:8081"))
				.route("seat_service", r -> r.path("/seat/**")
						.uri("http://localhost:8082"))
				.route("quota_service", r -> r.path("/quota/**")
						.uri("http://localhost:8084"))
				.route("admission_service", r -> r.path("/admission/**")
						.uri("http://localhost:8083"))
				.route("notification_service", r -> r.path("/notify/**")
						.uri("http://localhost:8085"))
				.build();
	}


}
