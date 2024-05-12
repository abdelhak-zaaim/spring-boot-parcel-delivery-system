/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 17:08
 *  * @modified : 24/04/2024, 17:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.controller;

import com.fsdm.pfe.delix.dto.request.RegisterRequestDto;
import com.fsdm.pfe.delix.dto.response.LoginResponseDto;
import com.fsdm.pfe.delix.dto.response.MessageDto;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.exception.personalizedexceptions.UserRegistrationException;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Controller
public class AuthenticationController {
    private final CustomerServiceImpl customerService;

    private final AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public AuthenticationController(CustomerServiceImpl customerService,@Qualifier("authenticationManagerUser") AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping("/login")
//    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
//
//        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
//                loginRequest.username(), loginRequest.password());
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authentication);
//
//
//        securityContextRepository.saveContext(context, request, response);
//    }





    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest , HttpServletRequest request, HttpServletResponse response) {
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
            return ResponseEntity.ok(new LoginResponseDto(true,authenticationResponse.isAuthenticated(), null, "Login successful"));
        } catch (AuthenticationException e) {
            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false,false, e.getMessage(), "Login failed"));
        }
    }



    public record LoginRequest(String username, String password) {
    }

    @GetMapping("/login")
    public String loginPage() {
        return "home/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "home/register";
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDto> registerAccount(@Valid RegisterRequestDto registerRequestDto) {

        try {
            Customer customer = customerService.registerCustomer(registerRequestDto);
            MessageDto messageDto = new MessageDto(200, "Account created successfully , please check your email to verify your account");
            messageDto.setData(customer);
            return ResponseEntity.ok(messageDto);
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(new MessageDto(300, e.getMessage()));
        }
    }


    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        if (token == null || token.isEmpty()) {
            return "/home/index";
        }
        if (customerService.verifyEmail(token)) {
            return "home/verified";
        }

        return "home/index";
    }


    @GetMapping("/test/index")
    public String index() {
        return "home/index_if_login";
    }

}
