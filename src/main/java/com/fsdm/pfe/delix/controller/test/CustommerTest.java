/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 14:08
 *  * @modified : 27/04/2024, 14:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.controller.test;

import com.fsdm.pfe.delix.entity.Address;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.model.enums.UserStatus;
import com.fsdm.pfe.delix.service.AddressService;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class CustommerTest {
    private CustomerServiceImpl customerService;
    private AddressService addressService;

    public CustommerTest(CustomerServiceImpl customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }



    @GetMapping("/test/customer/update")
    public ResponseEntity<Customer> updateCustomer() {
        Customer customer = customerService.loadCustomerById(1L);
        customer.setEmail("abdelhak@tset.com");
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @GetMapping("/test/customer/get")
    public ResponseEntity<Customer> getCustomer() {
        Customer customer = customerService.loadCustomerById(1L);
        return ResponseEntity.ok(customer);

    }
}


