/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 16:27
 *  * @modified : 18/05/2024, 16:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller;

import com.fsdm.pfe.delix.dto.request.UpdatePasswordRequestDto;
import com.fsdm.pfe.delix.dto.request.UpdateProfileRequestDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.model.enums.Role;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Collection;

@Controller
public class CustomerProfileController {
    private final AuthenticationManager authenticationManager;

    private final CustomerServiceImpl customerService;
    private final Validator validator;
    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public CustomerProfileController(@Qualifier("authenticationManagerUser") AuthenticationManager authenticationManager, CustomerServiceImpl customerService, Validator validator) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.validator = validator;
    }

    @GetMapping("/express/profile")
    public String expressProfile() {
        return "home/profile";
    }

    @PostMapping("/express/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequestDto updateProfileRequestDto, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("updateProfileRequestDto = " + updateProfileRequestDto);
        DataBinder binder = new DataBinder(updateProfileRequestDto);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();
        if (result.hasErrors()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("please verify the inputs").build());
        }
        try {
            Principal principal = request.getUserPrincipal();

            if (principal == null) {
                return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error("You are not authorized to perform this action").message("You are not authorized to perform this action").build());
            } else {
                customerService.updateCustomerProfile(principal.getName(), updateProfileRequestDto);

                return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(true).error(null).message("Profile updated successfully").build());
            }


        } catch (ResponseStatusException e) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message("An error occurred").build());
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message("An error occurred").build());
        }
    }

    @PostMapping("/express/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto, HttpServletRequest request, HttpServletResponse response) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error("You are not authorized to perform this action").message("You are not authorized to perform this action").build());
        } else {
            DataBinder binder = new DataBinder(updatePasswordRequestDto);
            binder.setValidator(validator);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()) {
                return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("please verify the inputs").build());
            }
            try {
                customerService.updatePassword(principal.getName(), updatePasswordRequestDto);
                return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(true).error(null).message("Password updated successfully").build());
            } catch (ResponseStatusException e) {
                return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message(e.getReason()).build());
            } catch (Exception e) {
                return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message("An error occurred").build());
            }
        }




    }


}
