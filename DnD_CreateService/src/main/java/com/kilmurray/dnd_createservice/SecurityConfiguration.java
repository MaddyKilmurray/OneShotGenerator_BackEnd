//package com.kilmurray.dnd_createservice;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        // @formatter:off
//        http
//                .authorizeExchange()
//                .anyExchange().authenticated()
//                .and()
//                .oauth2Login()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//        // @formatter:on
//    }
//
////    @Bean
////    CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration corsConfig = new CorsConfiguration();
////        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
////        corsConfig.setMaxAge(3600L);
////        corsConfig.addAllowedMethod("*");
////        corsConfig.addAllowedHeader("*");
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", corsConfig);
////        return source;
////    }
//}
