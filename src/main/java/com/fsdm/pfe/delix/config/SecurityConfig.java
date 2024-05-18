/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 16:45
 *  * @modified : 08/05/2024, 16:45
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.config;

import com.fsdm.pfe.delix.config.jwt.AuthTokenFilter;
import com.fsdm.pfe.delix.repository.DeliveryManRepo;
import com.fsdm.pfe.delix.repository.EmployeeRepo;
import com.fsdm.pfe.delix.repository.TransporterRepo;
import com.fsdm.pfe.delix.repository.VehicleOperatorEmployeeRepo;
import com.fsdm.pfe.delix.service.DeliveryManService;
import com.fsdm.pfe.delix.service.Impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import com.fsdm.pfe.delix.model.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;



@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class AdminSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChainAdmin( HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/admin", "/admin/**").authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                            {
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin*")).hasRole(Role.ADMIN_ROLE);
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin/login")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasRole(Role.ADMIN_ROLE);
                            }

                    ).securityMatcher("/admin", "/admin/**")

                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").deleteCookies("JSESSIONID"))
                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->

                            {
                                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> {

                                    response.sendRedirect("/admin/login");
                                });


                            }
                    );


            return http.build();
        }


        @Bean
        @Primary
        public AuthenticationManager authenticationManagerAdmin(
                AdminServiceImpl adminService,
                PasswordEncoder passwordEncoder) {

            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(adminService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(authenticationProvider);
        }


    }


    @Configuration
    @Order(2)

    public static class UserSecurityConfig {

        private final PasswordEncoder passwordEncoder;
        private final CustomerServiceImpl customerService;

        public UserSecurityConfig(PasswordEncoder passwordEncoder, CustomerServiceImpl customerService) {
            this.passwordEncoder = passwordEncoder;
            this.customerService = customerService;
        }

        @Bean
        public SecurityFilterChain filterChainApp2(AuthenticationManager authenticationManager, HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {


            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/public/**", "/express/**","/","/login","/logout","/register","/order-quote").authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                            {
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/express/location/city")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/express/location/area")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/test/**")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/register*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/login*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/logout*")).hasRole(Role.CUSTOMER_ROLE);
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/verify*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/public/**")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/order-quote")).permitAll();

                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/express/**")).hasRole(Role.CUSTOMER_ROLE);
                            }
                    ).securityMatcher("/public/**", "/express/**","/","/login","/logout","/register","/order-quote","/verify","/test/**")
                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID"))


                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                            {
                                httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/admin/accessDenied");
                                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> {
                                    //  request.getRequestDispatcher("/login").forward(request, response);

                                    response.sendRedirect("/login");
                                });


                            }
                    );

            return http.build();


        }


        @Bean
        public AuthenticationManager authenticationManagerUser() {

            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(customerService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(authenticationProvider);
        }


    }

    @Configuration
    @Order(3)
    public static class ApiSecurityConfigDelivery {

        private final AuthTokenFilter authFilter;
        private final VehicleOperatorEmployeeRepo vehicleOperatorEmployeeRepo;

        public ApiSecurityConfigDelivery(AuthTokenFilter authFilter, VehicleOperatorEmployeeRepo vehicleOperatorEmployeeRepo) {
            this.authFilter = authFilter;


            this.vehicleOperatorEmployeeRepo = vehicleOperatorEmployeeRepo;
        }
        @Bean
        public UserDetailsService userDetailsServiceApi() {
            return new VehicleOperatorEmployeeServiceImpl(vehicleOperatorEmployeeRepo);
        }


        @Bean
        public SecurityFilterChain filterChainApp3(AuthenticationManager authenticationManager, HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/api/delivery/**").authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                            {
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/api/delivery/login")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers("/api/delivery/**").hasRole("DELIVERY");

                            }
                    ).csrf(csrf -> csrf.disable()).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProviderApi()).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).securityMatcher("/api/delivery/**");

            return http.build();


        }



        @Bean

        public AuthenticationProvider authenticationProviderApi() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsServiceApi());
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }

        @Bean
        @Qualifier("authenticationManagerApiDelivery")
        public AuthenticationManager authenticationManagerApi(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

    }





}