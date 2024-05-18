///*
// *
// *  * @project : DeliX
// *  * @created : 18/05/2024, 13:33
// *  * @modified : 18/05/2024, 13:33
// *  * @description : This file is part of the DeliX project.
// *  * @license : MIT License
// *
// */
//
//package com.fsdm.pfe.delix.config;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@SpringBootApplication
//@EnableSwagger2
//public class SwaggerConfig {
//    public static void main(String[] args) {
//        SpringApplication.run(SwaggerConfig.class, args);
//    }
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.fsdm.pfe.delix.controller.api.AuthenticationControllerDelivery")).build();
//    }
//}