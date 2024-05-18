/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:21
 *  * @modified : 01/05/2024, 19:21
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.dto.request.RegisterRequestDto;
import com.fsdm.pfe.delix.dto.request.UpdatePasswordRequestDto;
import com.fsdm.pfe.delix.entity.Customer;
import io.netty.util.internal.ObjectPool;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface CustomerService {
    void deleteCustomer(Long id);

    Customer loadCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer registerCustomer(RegisterRequestDto registerRequestDto , String baseUrl);

    Optional<Customer> loadByEmail(String email);

    void logoutCustomer(Authentication auth);
    Optional<Customer> updatePassword(String email, UpdatePasswordRequestDto updatePasswordRequestDto);
}
