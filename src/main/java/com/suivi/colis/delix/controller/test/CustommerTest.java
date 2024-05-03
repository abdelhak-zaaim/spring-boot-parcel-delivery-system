/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 14:08
 *  * @modified : 27/04/2024, 14:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.controller.test;

import com.suivi.colis.delix.entity.Address;
import com.suivi.colis.delix.entity.Customer;
import com.suivi.colis.delix.model.enums.UserStatus;
import com.suivi.colis.delix.service.AddressService;
import com.suivi.colis.delix.service.Impl.CustomerServiceImpl;
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

    @GetMapping("/test/customer/add")
    public ResponseEntity<Customer> addCustommer() {
        Address address = new Address("marjaa","fes","fes-maknes","30000","morocco");
        address = addressService.saveAddress(address);

        //addding a custommer for testing purposes
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setName("test");
        customer.setPassword("12345678");
        customer.setPhoneNumber("123456789");
        customer.setStatus(UserStatus.ACTIVE);
        customer.setCin("ZH6952");
        customer.setAddress(address);
        customer.setDateOfBirth(new Date("09/01/2002"));
        try {
            return ResponseEntity.ok(customerService.saveCustomer(customer));

        } catch (Exception e) {
            throw new IllegalIdentifierException("Failed to add customer");
        }

    }

    @GetMapping("/test/custommer/update")
    public ResponseEntity<Customer> updateCustommer() {
        //updating a custommer for testing purposes
        Customer customer = customerService.loadCustomerById(1L);
        customer.setEmail("abdelhak@tset.com");
        return ResponseEntity.ok(customerService.updateCustomer(customer));

    }
}


