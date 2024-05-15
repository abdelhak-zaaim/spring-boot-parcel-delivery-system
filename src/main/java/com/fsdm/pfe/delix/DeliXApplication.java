package com.fsdm.pfe.delix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeliXApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliXApplication.class, args);
    }

}
