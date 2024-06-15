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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SessionRegistry sessionRegistry = new SessionRegistryImpl();

    public AdminAuthenticationController(@Qualifier("authenticationManagerAdmin") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }


    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationController.LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            // Create an authentication request using the provided username and password
            Authentication authenticationRequest =
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

            // Attempt to authenticate the user
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            securityContextRepository.saveContext(context, request, response);

            sessionRegistry.registerNewSession(request.getSession().getId(), authenticationResponse.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDto(true, authenticationResponse.isAuthenticated(), null, "Login successful"));


        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, e.getMessage(), e.getMessage()));
        } catch (BadCredentialsException e) {
            // Handle incorrect password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, e.getMessage(), "Email ou mot de passe incorrect"));
        } catch (AuthenticationException e) {
            // Handle other authentication failures
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, e.getMessage(), e.getMessage()));
        }

    }


    @GetMapping("/403")
    public String accessDenied() {
        return "error/404";
    }

    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }

    @GetMapping("/admin/accessDenied")
    public String accessDeniedAdmin() {
        return "admin/accessDenied";
    }
}
