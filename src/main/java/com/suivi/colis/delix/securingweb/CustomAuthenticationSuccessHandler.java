/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 18:12
 *  * @modified : 27/04/2024, 18:12
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.securingweb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin/home");
                } catch (Exception e) {


                }

            } else if (authority.getAuthority().equals("CUSTOMER")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/customer/home");
                } catch (Exception e) {


                }
            } else {
                throw new IllegalStateException();
            }
        });
    }
}