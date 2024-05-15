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

import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.service.Impl.AdminServiceImpl;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import com.fsdm.pfe.delix.model.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.server.ResponseStatusException;
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
        public SecurityFilterChain securityFilterChainAdmin(AuthenticationManager authenticationManager, HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/admin*", "/admin/**").authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                            {


                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin*")).hasRole(Role.ADMIN_ROLE);
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin/login")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasRole(Role.ADMIN_ROLE);
                            }

                    ).securityMatcher("/admin", "/admin/**")

                    // .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/admin/login").loginProcessingUrl("/admin/login").failureUrl("/admin/login?error=true").defaultSuccessUrl("/admin/"))
                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").deleteCookies("JSESSIONID"))
                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->

                            {
                                httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403");
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
            http.securityMatcher("/public/**", "/express/**", "/*", "/express/location/city*").authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                            {
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/express/location/city")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/express/location/area")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/test/**")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/register*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/login*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/verify*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/403")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/404")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/public/**")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/order-quote")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/express/**")).hasRole(Role.CUSTOMER_ROLE);

                            }
                    ).securityMatcher("/public/**", "/express/**", "/*")

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


}