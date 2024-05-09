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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminAuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AdminAuthenticationController(@Qualifier("authenticationManagerAdmin") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }


    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationController.LoginRequest loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse.toString());
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
