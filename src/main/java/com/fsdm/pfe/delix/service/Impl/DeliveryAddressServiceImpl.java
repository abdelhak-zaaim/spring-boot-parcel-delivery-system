
/*
 * **
 *  * @project : DeliX
 *  * @created : 30/04/2024, 19:00
 *  * @modified : 30/04/2024, 19:00
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.dto.request.DeliveryAddressRequestDto;
import com.fsdm.pfe.delix.entity.Area;
import com.fsdm.pfe.delix.entity.DeliveryAddress;
import com.fsdm.pfe.delix.repository.DeliveryAddressRepo;
import com.fsdm.pfe.delix.service.DeliveryAddressService;
import com.fsdm.pfe.delix.service.Impl.location.AreaServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final AreaServiceImpl areaService;
    DeliveryAddressRepo deliveryAddressRepo;

    public DeliveryAddressServiceImpl(DeliveryAddressRepo deliveryAddressRepo, AreaServiceImpl areaService) {
        this.deliveryAddressRepo = deliveryAddressRepo;
        this.areaService = areaService;
    }

    @Override
    public void deleteDeliveryAddress(Long id) {
        deliveryAddressRepo.deleteById(id);
    }

    public DeliveryAddress loadDeliveryAddress(Long id) {
        return deliveryAddressRepo.findById(id).orElse(null);
    }

    @Override
    public DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        return deliveryAddressRepo.save(deliveryAddress);
    }

    @Override
    public DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        return deliveryAddressRepo.save(deliveryAddress);
    }

    public DeliveryAddress convertRequestToEntity(DeliveryAddressRequestDto deliveryAddressRequestDto) {

        Area area = areaService.loadByCode(deliveryAddressRequestDto.getArea());

        if (area == null) {
            throw new IllegalArgumentException("Area not found");
        }

        return new DeliveryAddress(
                deliveryAddressRequestDto.getId(),
                area,
                deliveryAddressRequestDto.getAddress(),
                deliveryAddressRequestDto.getContactFirstName(),
                deliveryAddressRequestDto.getContactLastName(),
                deliveryAddressRequestDto.getContactNumber(),
                deliveryAddressRequestDto.getContactEmail()
        );
    }


}
