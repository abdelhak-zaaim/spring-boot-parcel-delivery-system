/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 16:32
 *  * @modified : 16/05/2024, 16:32
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.DeliveryMan;
import com.fsdm.pfe.delix.repository.DeliveryManRepo;
import com.fsdm.pfe.delix.service.DeliveryManService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DeliveryManServiceImpl implements DeliveryManService {
    private final DeliveryManRepo deliveryManRepo;
    private final UserServiceImpl userService;

    public DeliveryManServiceImpl(DeliveryManRepo deliveryManRepo, UserServiceImpl userService) {
        this.deliveryManRepo = deliveryManRepo;
        this.userService = userService;
    }

    @Override
    public DeliveryMan addNewDeliveryMan(DeliveryMan deliveryMan) {
        deliveryMan.setPassword(userService.encodePassword(deliveryMan.getPassword()));
        return deliveryManRepo.save(deliveryMan);
    }

    @Override
    public void updateDeliveryMan(DeliveryMan deliveryMan) {

    }

    @Override
    public void deleteDeliveryMan(DeliveryMan deliveryMan) {

    }

    @Override
    public DeliveryMan getDeliveryManById(Long id) {
        return null;
    }

    @Override
    public DeliveryMan getDeliveryManByEmail(String email) {
        return null;
    }

    @Override
    public DeliveryMan getDeliveryManByLicenseNumber(String licenseNumber) {
        return null;
    }

    @Override
    public DeliveryMan getDeliveryManByVehicularMatriculate(String vehicularMatriculate) {
        return null;
    }

    @Override
    public DeliveryMan getDeliveryManByVehicleType(String vehicleType) {
        return null;
    }

    @Override
    public DeliveryMan getDeliveryManByLocationPoint(String locationPoint) {
        return null;
    }

    @Override
    public DeliveryMan getDeliveryManByDeliveryArea(String deliveryArea) {
        return null;
    }

    @Override
    public Collection<DeliveryMan> getAllDeliveryMan() {
        return List.of();
    }

    @Override
    public Collection<DeliveryMan> getDeliveryManByAssociatedAgency(String associatedAgency) {
        return List.of();
    }


}
