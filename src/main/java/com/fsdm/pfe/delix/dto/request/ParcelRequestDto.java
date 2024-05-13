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
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;


import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Parcel}
 */
@Value

@AllArgsConstructor
public class ParcelRequestDto implements Serializable {
    Long id;

    @NotNull
    @Min(1)
    float height;

    @NotNull
    @Min(1)
    float width;
    @NotNull
    @Min(1)
    float length;

    @NotNull

    @Min(1)
    float weight;

    @NotNull

    ParcelType type;
    int appointmentTime;

    @Valid
    DeliveryAddressRequestDto pickupRequestAddress;
    @Valid
    DeliveryAddressRequestDto receiverAddress;

}