/*
 *
 *  * @project : DeliX
 *  * @created : 12/05/2024, 20:08
 *  * @modified : 12/05/2024, 20:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.model.enums.ParcelType;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Parcel}
 */
@Value
public class ParcelRequestDto implements Serializable {
    Long id;

    float height;
    float width;
    float length;

    float weight;

    ParcelType type;
    int appointmentTime;
    DeliveryAddressDto pickupRequestAddress;
    DeliveryAddressDto receiverAddress;

}