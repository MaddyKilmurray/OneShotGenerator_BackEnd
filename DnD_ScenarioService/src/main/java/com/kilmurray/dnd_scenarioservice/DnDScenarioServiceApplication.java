package com.kilmurray.dnd_scenarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DnDScenarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDScenarioServiceApplication.class, args);
    }

}
