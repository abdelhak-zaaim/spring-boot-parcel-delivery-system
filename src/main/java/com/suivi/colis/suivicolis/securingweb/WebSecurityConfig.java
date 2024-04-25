/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 16:45
 *  * @modified : 24/04/2024, 16:45
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.securingweb;


import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/", "/home/**","/test/**").permitAll() // the /test/** paths for testing todo: delete it after testing
                .requestMatchers("/customer/**").hasRole(Role.CUSTOMER_ROLE)
                .requestMatchers("/delivery/**").hasRole(Role.DELIVERY_MAN_ROLE)
                .requestMatchers("/agency/**").hasRole(Role.AGENCY_EMPLOYEE_ROLE)
                .requestMatchers("/admin/**").hasRole(Role.ADMIN_ROLE)


                .anyRequest().authenticated()).formLogin((form) -> form.loginPage("/login").usernameParameter("username").passwordParameter("password")

                .failureForwardUrl("/login?error=true").failureUrl("/login?error=true")

                .defaultSuccessUrl("/test/loginSuccess", true) // for testing todo: chhange this line after testing

                .permitAll()).logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/api/**", "/font-awesome/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserService userDetailsService() {
        return userService;
    }
}

