package com.kilmurray.dnd_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/character/**")
                        .uri("lb://CHARACTER-SERVICE"))
                .route(p -> p.path("/api/create/**")
                        .uri("lb://CREATE-SERVICE"))
                .route(p -> p.path("/api/random/**")
                        .uri("lb://RANDOMISER-SERVICE"))
                .route(p -> p.path("/api/user/**")
                        .uri("lb://USER-SERVICE"))
                .build();
    }
}
