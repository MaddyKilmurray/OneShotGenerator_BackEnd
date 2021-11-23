package com.kilmurray.dnd_characterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DnDCharacterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDCharacterServiceApplication.class, args);
    }

}
