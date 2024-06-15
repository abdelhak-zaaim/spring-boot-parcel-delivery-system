package com.fsdm.pfe.delix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
@EnableCaching
public class DeliXApplication {

    public static void main(String[] args) {

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("lang/messages");
        messageSource.setDefaultEncoding("UTF-8");

        SpringApplication.run(DeliXApplication.class, args);
    }

}
