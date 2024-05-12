
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final CustomerRepo customerRepository;
    private final UserServiceImpl userService;
    private final VerificationTokenServiceImpl verificationTokenService;

    public CustomerServiceImpl(CustomerRepo customerRepository, UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService) {
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
        customer.setPassword(userService.encodePassword(customer.getPassword()));
        customer = customerRepository.save(customer);

        if (customer == null) {
            throw new UserRegistrationException("User Registration Failed");
        } else {
            userService.sendEmailVerificationToken(customer.getEmail(), verificationTokenService.createVerification(customer).getToken());
        }
        return customer;
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
}
