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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;


import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Parcel}
 */
@Value

public class ParcelRequestDto implements Serializable {
    Long id;
    @NotNull
    @NotEmpty
    float height;
    @NotEmpty
    @NotNull

    float width;
    @NotNull
    @NotEmpty
    float length;
    @NotEmpty
    @NotNull

    @Min(1)
    float weight;
    @NotEmpty
    @NotNull
    ParcelType type;
    int appointmentTime;
    @NotEmpty
    @NotNull
    DeliveryAddressDto pickupRequestAddress;
    @NotNull
    DeliveryAddressDto receiverAddress;
}