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

    public DeliveryAddressResponseDto(DeliveryAddress deliveryAddress) {
        this.contactEmail = deliveryAddress.getContactEmail();
        this.contactNumber = deliveryAddress.getContactNumber();
        this.contactName = deliveryAddress.getContactName();
        this.postalCode = deliveryAddress.getPostalCode();
        this.country = deliveryAddress.getCountry();
        this.city = deliveryAddress.getCity();
        this.state = deliveryAddress.getState();
        this.address = deliveryAddress.getAddress();
        this.id = deliveryAddress.getId();
    }

    Long id;
    String address;
    String state;
    String city;
    String country;
    String postalCode;
    String contactName;
    String contactNumber;
    String contactEmail;
}