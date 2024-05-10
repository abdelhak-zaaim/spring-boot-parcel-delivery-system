/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 16:45
 *  * @modified : 08/05/2024, 16:45
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.securingweb;

import com.fsdm.pfe.delix.service.Impl.AdminServiceImpl;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import com.fsdm.pfe.delix.model.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class AdminSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChainAdmin(@Qualifier("adminAuthenticationManager") AuthenticationManager authenticationManager, HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/admin*", "/admin/**").authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                            {
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin*")).hasRole(Role.ADMIN_ROLE);
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin/login")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasRole(Role.ADMIN_ROLE);
                            }
                    ).securityMatcher("/admin", "/admin/**")

                    .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/admin/login").loginProcessingUrl("/admin/login").failureUrl("/admin/login?error=true").defaultSuccessUrl("/admin/"))
                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").deleteCookies("JSESSIONID"))
                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                            httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/admin/403"));

            return http.build();
        }


        @Bean
        @Qualifier("adminAuthenticationManager")
        @Primary
        public AuthenticationManager authenticationManagerAdmin(
                AdminServiceImpl adminService,
                PasswordEncoder passwordEncoder) {
            System.out.println("AAdmin /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");

            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(adminService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(authenticationProvider);
        }


    }


    @Configuration
    @Order(2)
    public static class UserSecurityConfig {

        @Bean
        public SecurityFilterChain filterChainApp2(@Qualifier("userAuthenticationManager") AuthenticationManager authenticationManager, HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/*").authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/user*")).hasRole("USER")).securityMatcher("/*")


                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {

                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/test/**")).permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/register*")).permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/login*")).permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/verify*")).permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/403")).permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/404")).permitAll();
                    })

                    // log in
                    .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error").defaultSuccessUrl("/"))
                    // logout
                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID")).exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"));
            return http.build();
        }


        @Bean
        @Qualifier("userAuthenticationManager")
        public AuthenticationManager authenticationManagerUser(
                CustomerServiceImpl customerService,
                PasswordEncoder passwordEncoder) {
            System.out.println("User /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");


            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(customerService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(authenticationProvider);
        }


    }


}
