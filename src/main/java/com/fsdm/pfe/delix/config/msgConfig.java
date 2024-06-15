/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 18:55
 *  * @modified : 30/05/2024, 18:55
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
public class msgConfig {

    @Bean("messageSource")

    public MessageSource messageSource() {

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasenames("lang/messages");

        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;

    }

    @Bean

    public LocaleResolver localeResolver() {

        return new CookieLocaleResolver();

    }

}