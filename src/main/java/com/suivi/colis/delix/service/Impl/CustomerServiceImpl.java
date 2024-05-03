
/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 19:30
 *  * @modified : 24/04/2024, 19:30
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.Customer;
import com.suivi.colis.delix.repository.CustomerRepo;

import com.suivi.colis.delix.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepository;
    private final UserServiceImpl userService;

    public CustomerServiceImpl(CustomerRepo customerRepository, UserServiceImpl userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
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

}
