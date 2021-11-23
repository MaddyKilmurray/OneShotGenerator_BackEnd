package com.kilmurry.dnd_discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DnDDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDDiscoveryServiceApplication.class, args);
    }

}
