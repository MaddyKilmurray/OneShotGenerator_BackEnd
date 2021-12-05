package com.kilmurray.dnd_userservice.security;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
                .pathMatchers("/api/users/authenticated/update").hasAnyRole("ADMIN","DM","PLAYER")
                .pathMatchers("/api/users/authenticated").hasAnyRole("ADMIN","DM","PLAYER")
                .pathMatchers("/api/users/**").hasAnyRole("ADMIN","DM","PLAYER")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
        // @formatter:on
    }
}
