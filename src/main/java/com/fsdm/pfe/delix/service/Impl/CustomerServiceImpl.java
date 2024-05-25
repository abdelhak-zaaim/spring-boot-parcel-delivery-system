
/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 19:30
 *  * @modified : 24/04/2024, 19:30
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.dto.request.LoginRequestDto;
import com.fsdm.pfe.delix.dto.request.RegisterRequestDto;
import com.fsdm.pfe.delix.dto.request.UpdatePasswordRequestDto;
import com.fsdm.pfe.delix.dto.request.UpdateProfileRequestDto;
import com.fsdm.pfe.delix.dto.response.LoginResponseDto;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.exception.personalizedexceptions.UserRegistrationException;
import com.fsdm.pfe.delix.model.enums.Role;
import com.fsdm.pfe.delix.repository.CustomerRepo;
import com.fsdm.pfe.delix.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepo customerRepository;
    private final UserServiceImpl userService;
    private final VerificationTokenServiceImpl verificationTokenService;

    private final LoginLogServiceImpl loginLogService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();


    public CustomerServiceImpl(PasswordEncoder passwordEncoder, CustomerRepo customerRepository, UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService, LoginLogServiceImpl loginLogService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.loginLogService = loginLogService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer loadCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setPassword(userService.encodePassword(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer registerCustomer(RegisterRequestDto registerRequestDto, String baseUrl) {

        if (!registerRequestDto.getPassword().equals(registerRequestDto.getRePassword())) {
            throw new UserRegistrationException("Passwords do not match");
        }


        if (customerRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new UserRegistrationException("User With this Email already exists");
        }


        Customer customer = new Customer(registerRequestDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer = customerRepository.save(customer);

        if (customer == null) {
            throw new UserRegistrationException("User Registration Failed");
        } else {
            Customer finalCustomer = customer;
            String token = verificationTokenService.createVerification(finalCustomer).getToken();
            new Thread(() -> {
                try {
                    userService.sendEmailVerification(finalCustomer.getEmail(), token, baseUrl, finalCustomer.getLastName());
                } catch (Exception ignored) {


                }
            }).start();
        }
        return customer;
    }

    @Override
    public Optional<Customer> loadByEmail(String email) {
        return customerRepository.findByEmail(email);
    }


    @Override
    public Customer loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepository.findByEmail(email);
        customer.orElseThrow(() -> new UsernameNotFoundException("User not found:" + email));


        return customer.get();

    }


    public boolean verifyEmail(String token) {

        return verificationTokenService.verifyEmail(token);
    }

    @Override
    public void logoutCustomer(Authentication auth) {
        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            boolean isCustomer = authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.getCustomerRoleName()));
            if (isCustomer) {
                SecurityContextHolder.clearContext();
            } else {
                throw new RuntimeException("Operation not allowed");
            }
        }
    }

    @Override

    @Transactional
    public void updatePassword(String email, UpdatePasswordRequestDto updatePasswordRequestDto) {
        if (!updatePasswordRequestDto.getNewPassword().equals(updatePasswordRequestDto.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }

        Optional<Customer> customer = customerRepository.findByEmail(email);

        if (customer.isPresent()) {
            if (!userService.passwordMatch(updatePasswordRequestDto.getPassword(), customer.get().getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Password");
            }
            customer.get().setPassword(userService.encodePassword(updatePasswordRequestDto.getNewPassword()));
            customerRepository.save(customer.get());

        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

    }


    public LoginResponseDto loginCustomer(LoginRequestDto loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            securityContextRepository.saveContext(context, request, response);

            if (authenticationResponse.isAuthenticated()) {
                Customer user = (Customer) authenticationResponse.getPrincipal();

                if (!user.isEmailVerified()) {
                    return new LoginResponseDto(false, false, "Email not verified", "Email not verified yet, please verify your email");
                }

                String ipAddress = request.getHeader("X-Forwarded-For");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }

                // Save login log
                loginLogService.saveLoginLog((User) authenticationResponse.getPrincipal(), request.getHeader("User-Agent"), ipAddress, true, "login");
            }

            return new LoginResponseDto(true, authenticationResponse.isAuthenticated(), null, "Login successful");

        } catch (UsernameNotFoundException e) {
            return new LoginResponseDto(false, false, e.getMessage(), e.getMessage());
        } catch (BadCredentialsException e) {
            // Handle incorrect password
            return new LoginResponseDto(false, false, e.getMessage(), "Incorrect Email or Password");
        } catch (AuthenticationException e) {
            // Handle other authentication failures
            return new LoginResponseDto(false, false, e.getMessage(), e.getMessage());
        }
    }


    public Customer updateCustomerProfile(String userName, UpdateProfileRequestDto customerProfile) {
        Customer customer = customerRepository.findByEmail(userName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        customer.setFirstName(customerProfile.getFirstName());
        customer.setLastName(customerProfile.getLastName());
        customer.setPhoneNumber(customerProfile.getPhoneNumber());
        return customerRepository.save(customer);
    }


    public List<Parcel> getParcelsForCustomerByUserName(@Email String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return customer.getParcels();
    }
}
