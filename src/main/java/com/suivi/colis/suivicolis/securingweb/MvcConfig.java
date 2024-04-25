/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 16:44
 *  * @modified : 24/04/2024, 16:44
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**",
                        "/css/**",
                        "/resources/**",
                        "/js/**",
                        "/images/**",
                        "/api/**",
                        "/font-awesome/**"

                )
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/images/",
                        "classpath:/static/api/",
                        "classpath:/resources/",
                        "classpath:/static/font-awesome/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {


        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }




}