package com.kilmurray.dnd_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
public class DnDGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("character-service", p -> p.path("/api/character/**")
                        .uri("lb://CHARACTER-SERVICE"))
                .route("create-service", p -> p.path("/api/create/**")
                        .uri("lb://CREATE-SERVICE"))
                .route("randomiser-service", p -> p.path("/api/random/**")
                        .uri("lb://RANDOMISER-SERVICE"))
                .route("user-service", p -> p.path("/api/users/**")
                        .uri("lb://USER-SERVICE"))
                .build();
    }

}
