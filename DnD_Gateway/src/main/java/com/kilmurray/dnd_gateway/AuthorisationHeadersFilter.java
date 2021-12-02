package com.kilmurray.dnd_gateway;

import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthorisationHeadersFilter extends AbstractGatewayFilterFactory<AuthorisationHeadersFilter.Config> {


    private final Environment environment;

    public AuthorisationHeadersFilter(Environment environment) {
        this.environment = environment;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain)-> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Can't access resource!");
            }
            String tokenWithBearer = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = tokenWithBearer.replace("Bearer", "");
            if (!isJwtValid(jwt)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Can't access resource!");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }

    private boolean isJwtValid(String jwt){
        boolean returnValue = true;
        String subject = Jwts.parser().setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
        if (subject == null || subject.isEmpty()){
            returnValue = false;
        }
        return returnValue;
    }
}
