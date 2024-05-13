
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

import com.fsdm.pfe.delix.dto.request.RegisterRequestDto;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.exception.personalizedexceptions.UserRegistrationException;
import com.fsdm.pfe.delix.repository.CustomerRepo;

import com.fsdm.pfe.delix.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepo customerRepository;
    private final UserServiceImpl userService;
    private final VerificationTokenServiceImpl verificationTokenService;

    public CustomerServiceImpl(PasswordEncoder passwordEncoder, CustomerRepo customerRepository, UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
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
    public Customer registerCustomer(RegisterRequestDto registerRequestDto) {

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
            userService.sendEmailVerificationToken(customer.getEmail(), verificationTokenService.createVerification(customer).getToken());
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




        System.out.println( "fghrgbrgirg////////////////////////////////////////////"+customer.get().getPassword());


        return customer.get();

    }


    public boolean verifyEmail(String token) {

        return verificationTokenService.verifyEmail(token);
    }


}
