/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 16:30
 *  * @modified : 16/05/2024, 16:30
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.DeliveryMan;

import java.util.Collection;

public interface DeliveryManService {
    DeliveryMan addNewDeliveryMan(DeliveryMan deliveryMan);

    void updateDeliveryMan(DeliveryMan deliveryMan);

    void deleteDeliveryMan(DeliveryMan deliveryMan);

    DeliveryMan getDeliveryManById(Long id);

    DeliveryMan getDeliveryManByEmail(String email);

    DeliveryMan getDeliveryManByLicenseNumber(String licenseNumber);

    DeliveryMan getDeliveryManByVehicularMatriculate(String vehicularMatriculate);

    DeliveryMan getDeliveryManByVehicleType(String vehicleType);

    DeliveryMan getDeliveryManByLocationPoint(String locationPoint);

    DeliveryMan getDeliveryManByDeliveryArea(String deliveryArea);

    Collection<DeliveryMan> getAllDeliveryMan();

    Collection<DeliveryMan> getDeliveryManByAssociatedAgency(String associatedAgency);
}
