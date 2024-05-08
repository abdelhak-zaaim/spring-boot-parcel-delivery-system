/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 16:45
 *  * @modified : 08/05/2024, 16:45
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.suivi.colis.delix.securingweb;

import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import com.suivi.colis.delix.model.enums.Role;
import com.suivi.colis.delix.service.Impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

//   @Bean
//   public UserDetailsService userDetailsService() {
//      InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//      manager.createUser(User.withUsername("user").password(passwordEncoder().encode("userPass")).roles("USER").build());
//      manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN").build());
//      return manager;
//   }

    @Bean
    public AuthenticationManager authenticationManagerAdmin(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class AdminSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
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

                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/admin/logout").logoutSuccessUrl("/protectedLinks").deleteCookies("JSESSIONID"))
                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                            httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/admin/403"))


            ;

            return http.build();
        }


//      @Bean
//      public AuthenticationManager authenticationManagerAdmin(
//              UserServiceImpl userService,
//              PasswordEncoder passwordEncoder) {
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//         authenticationProvider.setUserDetailsService(userService);
//         authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//         return new ProviderManager(authenticationProvider);
//      }


    }


    @Configuration
    @Order(2)
    public static class UserSecurityConfig {

        @Bean
        public SecurityFilterChain filterChainApp2(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/*").authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/user*")).hasRole("USER")).securityMatcher("/*")


                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {

                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/test/**")).permitAll();

                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/login*")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/403")).permitAll();
                                authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/404")).permitAll();
                            }

                    )

                    // log in
                    .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error=loginError").defaultSuccessUrl("/"))
                    // logout
                    .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/logout").logoutSuccessUrl("/protectedLinks").deleteCookies("JSESSIONID")).exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"));
            return http.build();
        }


//      @Bean
//      public AuthenticationManager authenticationManagerUser(
//              UserServiceImpl userService,
//              PasswordEncoder passwordEncoder) {
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//         authenticationProvider.setUserDetailsService(userService);
//         authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//         return new ProviderManager(authenticationProvider);
//      }


    }


}
