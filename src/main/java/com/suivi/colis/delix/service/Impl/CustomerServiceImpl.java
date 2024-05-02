
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

    public CustomerServiceImpl(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
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

        return customerRepository.save(customer);

    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
