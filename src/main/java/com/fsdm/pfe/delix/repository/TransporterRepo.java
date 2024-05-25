/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 20:19
 *  * @modified : 17/05/2024, 20:19
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Transporter;
import com.fsdm.pfe.delix.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransporterRepo extends JpaRepository<Transporter, Long> {
    Optional<Transporter> findByEmail(String email);

    Optional<Transporter> findByVehicleType(VehicleType vehicleType);

    Optional<Transporter> findByCin(String cin);

    Optional<Transporter> findByPhoneNumber(String phoneNumber);
}
