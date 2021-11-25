//package com.kilmurray.dnd_userservice.security;
//
//import com.kilmurray.dnd_userservice.security.filter.CustomAuthenticationFilter;
//import com.kilmurray.dnd_userservice.security.filter.CustomAuthorisationFilter;
//import com.kilmurray.dnd_userservice.security.service.CustomUserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//
//import static org.springframework.http.HttpMethod.*;
//import static org.springframework.http.HttpMethod.GET;
//
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@RequiredArgsConstructor
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailService customUserDetailService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:4200");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .userDetailsService(customUserDetailService)
////                .passwordEncoder(passwordEncoder);
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic();
//        http.cors().and().csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests()
//
////                // Public Access
////                .mvcMatchers(
////                        "/login",
////                        "/movie-app/users/register",
////                        "/movie-app/users/validate/username",
////                        "/movie-app/users/validate/email"
////                ).permitAll()
////
////                // Admin Exclusive Access
////                .mvcMatchers("/movie-app/playlists/admin/**").hasRole("ADMIN")
////                .mvcMatchers("/movie-app/users/admin/**").hasRole("ADMIN")
////
////                // Logged User Access
////                .mvcMatchers(GET, "/movie-app/users/authenticated").hasAnyRole("ADMIN", "USER")
////                .mvcMatchers(PATCH, "/movie-app/users/authenticated/set").hasAnyRole("ADMIN", "USER")
////                .mvcMatchers(POST, "/movie-app/users/authenticated/createPlaylist").hasAnyRole("ADMIN", "USER")
////                .mvcMatchers(POST, "/movie-app/users/authenticated/createPlaylistWithMovie").hasAnyRole("ADMIN", "USER")
////                .mvcMatchers(GET, "/movie-app/playlists/user/authenticated").hasAnyRole("ADMIN", "USER")
////                .mvcMatchers(GET, "/movie-app/playlists/visible").hasAnyRole("ADMIN", "USER") // manually secured on  controller
////                .mvcMatchers("/movie-app/playlists/{playlistId}/**").hasAnyRole("ADMIN", "USER") // manually secured on  controller
//
//
//                .anyRequest().authenticated();
//
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        http.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
