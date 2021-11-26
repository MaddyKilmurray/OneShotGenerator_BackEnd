package com.kilmurray.dnd_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DnDGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDGatewayApplication.class, args);
    }

}
