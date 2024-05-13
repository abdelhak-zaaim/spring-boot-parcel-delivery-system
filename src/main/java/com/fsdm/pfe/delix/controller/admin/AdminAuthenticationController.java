/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 15:49
 *  * @modified : 08/05/2024, 15:49
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller.admin;

import com.fsdm.pfe.delix.controller.AuthenticationController;
import com.fsdm.pfe.delix.dto.response.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();
    private SessionRegistry sessionRegistry = new SessionRegistryImpl();

    public AdminAuthenticationController(@Qualifier("authenticationManagerAdmin") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }


    @PostMapping("/admin/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request,HttpServletResponse response, Model model) {
        try {
            // Create an authentication request using the provided username and password
            Authentication authenticationRequest =
                    new UsernamePasswordAuthenticationToken(username, password);

            // Attempt to authenticate the user
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            securityContextRepository.saveContext(context, request, response);

            sessionRegistry.registerNewSession(request.getSession().getId(), authenticationResponse.getPrincipal());





            model.addAttribute("isLongedIn", authenticationResponse.isAuthenticated());
            model.addAttribute("message", "Login successful");

        } catch (AuthenticationException e) {
            // Handle authentication failure
        //    model.addAttribute("isLongedIn", false);
            model.addAttribute("message", e.getMessage());
        }

        return "admin/login";
    }


    @GetMapping("/403")
    public String accessDenied() {
        return "error/404";
    }
    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }
}
