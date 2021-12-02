package com.kilmurray.dnd_userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
@EnableEurekaClient
public class DnDUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDUserServiceApplication.class, args);
    }

}
