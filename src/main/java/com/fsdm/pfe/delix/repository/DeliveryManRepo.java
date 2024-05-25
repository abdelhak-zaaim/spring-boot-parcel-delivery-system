/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:47
 *  * @modified : 25/04/2024, 14:47
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryManRepo extends JpaRepository<DeliveryMan, Long> {
    Optional<DeliveryMan> findByEmail(String email);
}
