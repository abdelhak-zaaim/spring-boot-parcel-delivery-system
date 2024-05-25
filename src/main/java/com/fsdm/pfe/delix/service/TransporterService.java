/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 20:18
 *  * @modified : 17/05/2024, 20:18
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.Transporter;
import com.fsdm.pfe.delix.model.enums.VehicleType;

import java.util.Optional;

public interface TransporterService {
    void updateTransporter(Transporter transporter);

    void deleteTransporter(Transporter transporter);

    Optional<Transporter> loadByEmail(String email);

    Optional<Transporter> loadByPhoneNumber(String phoneNumber);

    Optional<Transporter> loadByCin(String cin);

    Optional<Transporter> loadByVehicleType(VehicleType vehicleType);
}
