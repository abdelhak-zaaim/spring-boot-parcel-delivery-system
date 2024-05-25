/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 16:51
 *  * @modified : 17/05/2024, 16:51
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.VehicleOperatorEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleOperatorEmployeeRepo extends JpaRepository<VehicleOperatorEmployee, Long> {
    Optional<VehicleOperatorEmployee> findByEmail(String email);

    Optional<VehicleOperatorEmployee> findByPhoneNumber(String phoneNumber);

    Optional<VehicleOperatorEmployee> findByCin(String cin);


    Optional<VehicleOperatorEmployee> findByVehicleType(String vehicleType);

    Optional<VehicleOperatorEmployee> findByRole(String role);
}
