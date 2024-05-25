/*
 *
 *  * @project : DeliX
 *  * @created : 09/05/2024, 21:38
 *  * @modified : 09/05/2024, 21:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.entity.DeliveryAddress;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.DeliveryAddress}
 */
@Value
public class DeliveryAddressResponseDto implements Serializable {

    Long id;
    String address;
    AreaResponseDto area;
    String contactNumber;
    String contactEmail;

    public DeliveryAddressResponseDto(DeliveryAddress deliveryAddress) {
        this.area = new AreaResponseDto(deliveryAddress.getArea());
        this.contactEmail = deliveryAddress.getContactEmail();
        this.contactNumber = deliveryAddress.getContactNumber();
        this.address = deliveryAddress.getAddress();
        this.id = deliveryAddress.getId();
    }
}