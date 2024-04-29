/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:51
 *  * @modified : 25/04/2024, 18:39
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:39
 *  * @modified : 25/04/2024, 15:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 19:30
 *  * @modified : 24/04/2024, 19:30
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.services;

import com.suivi.colis.suivicolis.entity.Customer;
import com.suivi.colis.suivicolis.repository.CustomerRepo;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepo customerRepository;
    public CustomerService(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void deleteCustomer(Long id) {
        try {

            customerRepository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer", e);
        }
    }

    public Customer addCustomer(Customer customer) {
        try {
           return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add customer", e);
        }
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update customer", e);
        }
    }

}
