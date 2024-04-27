/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 14:08
 *  * @modified : 27/04/2024, 14:08
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.controllers.test;

import com.suivi.colis.suivicolis.entities.Customer;
import com.suivi.colis.suivicolis.models.enums.UserStatus;
import com.suivi.colis.suivicolis.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class CustommerTest {
    private CustomerService customerService;

    public CustommerTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test/custommer/add")
    public ResponseEntity<Customer> addCustommer() {
        //addding a custommer fro testing purposes
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setName("abdelhak");
        customer.setPassword("12345678");
        customer.setPhoneNumber("123456789");
        customer.setStatus(UserStatus.ACTIVE);
        customer.setCin("ZH6952");
        customer.setDateOfBirth(new Date("09/01/2002"));
        try {
            return ResponseEntity.ok(customerService.addCustomer(customer));

        } catch (Exception e) {
            throw new RuntimeException("Failed to add customer", e);
        }

    }

}
