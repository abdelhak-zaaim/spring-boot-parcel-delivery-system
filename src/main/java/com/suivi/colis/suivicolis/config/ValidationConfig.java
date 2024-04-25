/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:01
 *  * @modified : 25/04/2024, 18:01
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig extends LocalValidatorFactoryBean{

    @Bean
    @Primary
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}