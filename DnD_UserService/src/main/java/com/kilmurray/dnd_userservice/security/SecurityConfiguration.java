package com.kilmurray.dnd_userservice.security;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.http.HttpMethod.GET;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        http
                .authorizeExchange()
                .pathMatchers("/api/users/register",
                        "/api/users/validate/email",
                        "/api/users/validate/username").permitAll()
                .pathMatchers(GET,"/api/users").hasAnyRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
        // @formatter:on
    }
}
