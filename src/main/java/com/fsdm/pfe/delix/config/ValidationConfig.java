/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 18:01
 *  * @modified : 25/04/2024, 18:01
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */
//
//package com.suivi.colis.suivicolis.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.view.InternalResourceView;
//
//@Configuration
//public class ValidationConfig extends LocalValidatorFactoryBean{
//    @Bean
//    public View error() {
//
//        return new InternalResourceView("/error.html");
//    }
//    @Bean
//    @Primary
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }
//}