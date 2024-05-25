/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 16:52
 *  * @modified : 17/05/2024, 16:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.VehicleOperatorEmployee;
import com.fsdm.pfe.delix.model.enums.VehicleType;

import java.util.Optional;

public interface VehicleOperatorEmployeeService {

    void updateVehicleOperatorEmployee(VehicleOperatorEmployee vehicleOperatorEmployee);

    void deleteVehicleOperatorEmployee(VehicleOperatorEmployee vehicleOperatorEmployee);

    Optional<VehicleOperatorEmployee> loadByEmail(String email);

    Optional<VehicleOperatorEmployee> loadByPhoneNumber(String phoneNumber);

    Optional<VehicleOperatorEmployee> loadByCin(String cin);

    Optional<VehicleOperatorEmployee> loadByVehicleType(VehicleType vehicleType);

    Optional<VehicleOperatorEmployee> loadByRole(String role);

    Optional<VehicleOperatorEmployee> loadById(Long id);
}
