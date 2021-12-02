package com.kilmurray.dnd_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class GatewayConfiguration {

    private final String CHARACTER_SERVICE_NAME = "CHARACTER-SERVICE";
    private final String USER_SERVICE_NAME = "USER-SERVICE";
    private final String CREATE_SERVICE_NAME = "CREATE-SERVICE";
    private final String RANDOMISER_SERVICE_NAME = "RANDOMISER-SERVICE";
    private final Environment environment;

    public GatewayConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/character/**")
                        .filters(f -> f.filter(new AuthorisationHeadersFilter(environment).apply(new AuthorisationHeadersFilter.Config())))
                        .uri("lb://CHARACTER-SERVICE"))
                .route(p -> p.path("/api/create/view")
                        .uri("lb://CREATE-SERVICE"))
                .route(p -> p.path("/api/create/save")
                        .filters(f -> f.filter(new AuthorisationHeadersFilter(environment).apply(new AuthorisationHeadersFilter.Config())))
                        .uri("lb://CREATE-SERVICE"))
                .route(p -> p.path("/api/random/**")
                        .uri("lb://RANDOMISER-SERVICE"))
                .route(p -> p.path("/api/user/**")
                        .filters(f -> f.filter(new AuthorisationHeadersFilter(environment).apply(new AuthorisationHeadersFilter.Config())))
                        .uri("lb://USER-SERVICE"))
                .build();
    }
}
