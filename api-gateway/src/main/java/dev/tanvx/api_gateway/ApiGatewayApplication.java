package dev.tanvx.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

	@Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/financial-advisor/auth-service/**")
                        .filters(f -> f.rewritePath("/financial-advisor/auth-service/?(?<segment>.*)",
                                        "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://AUTH-SERVICE"))
                .route(p -> p.path("/financial-advisor/profile-service/**")
                        .filters(f -> f.rewritePath("/financial-advisor/profile-service/?(?<segment>.*)",
										"/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://PROFILE-SERVICE"))
                .route(p -> p.path("/financial-advisor/transaction-service/**")
                        .filters(f -> f.rewritePath("/financial-advisor/transaction-service/?(?<segment>.*)",
										"/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://TRANSACTION-SERVICE"))
                .route(p -> p.path("/financial-advisor/notification-service/**")
                        .filters(f -> f.rewritePath("/financial-advisor/notification-service/?(?<segment>.*)",
										"/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://NOTIFICATION-SERVICE"))
                .build();
    }
}
