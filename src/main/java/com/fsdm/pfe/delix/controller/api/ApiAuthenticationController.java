/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 15:52
 *  * @modified : 17/05/2024, 15:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller.api;

import com.fsdm.pfe.delix.dto.api.ApiResponseDto;

import com.fsdm.pfe.delix.dto.api.authentication.AuthenticationRequestDto;
import com.fsdm.pfe.delix.dto.api.authentication.AuthenticationResponseDto;
import com.fsdm.pfe.delix.dto.response.LoginResponseDto;
import com.fsdm.pfe.delix.service.Impl.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApiAuthenticationController {
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiAuthenticationController(JwtServiceImpl jwtService, @Qualifier("authenticationManagerApi") AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/login")
    public ResponseEntity<ApiResponseDto> authenticateAndGetToken(@RequestBody AuthenticationRequestDto authRequest) {
        System.out.println("////////////////////////////////////////" + authRequest.toString());

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));


            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String token = jwtService.generateToken(userDetails);
                return ResponseEntity.ok(ApiResponseDto.builder().success(true).data(AuthenticationResponseDto.builder().token(token).role(userDetails.getAuthorities()).build()).message("Login successful").build());
            } else {
                throw new UsernameNotFoundException("Invalid credentials");
            }


        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(ApiResponseDto.builder().success(false).data(null).message("Invalid credentials").build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseDto.builder().success(false).data(null).message("An error occurred").build());

        }
    }
}
