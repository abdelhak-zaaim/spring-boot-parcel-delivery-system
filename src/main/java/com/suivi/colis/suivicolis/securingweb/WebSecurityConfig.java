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

import com.suivi.colis.suivicolis.model.enums.Role;
import com.suivi.colis.suivicolis.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private UserService userService;
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/", "/home/**","/login_page", "/test/**", "/test/custommer/add").permitAll() // the /test/** paths for testing todo: delete it after testing
                        .requestMatchers("/css/**","/js/**","/img/**").permitAll()
                        .requestMatchers("/customer/**").hasRole(Role.CUSTOMER_ROLE)
                        .requestMatchers("/delivery/**").hasRole(Role.DELIVERY_MAN_ROLE)
                        .requestMatchers("/agency/**").hasRole(Role.AGENCY_EMPLOYEE_ROLE)
                        .requestMatchers("/administration/**").hasAnyRole(Role.SUPER_ADMIN_RULE, Role.ADMIN_EMPLOYEE_ROLE)
                        .requestMatchers("/administration/users").hasAnyRole(Role.SUPER_ADMIN_RULE, Role.ADMIN_EMPLOYEE_ROLE)
                        .anyRequest().authenticated()).formLogin(Customizer.withDefaults())

                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

}

