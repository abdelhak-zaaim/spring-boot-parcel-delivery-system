/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 19:05
 *  * @modified : 24/04/2024, 19:05
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);


}
