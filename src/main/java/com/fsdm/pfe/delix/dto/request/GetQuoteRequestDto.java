/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 22:32
 *  * @modified : 13/05/2024, 22:32
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

@Value
public class GetQuoteRequestDto {
    @NotNull
    @NotBlank
    @NotEmpty
    String pickUpArea;
    @NotNull
    @NotBlank
    @NotEmpty
    String deliveryArea;
    @NotNull
    @Min(1)
    float weight;
    @NotNull
    @Min(1)
    float length;
    @NotNull
    @Min(1)
    float width;
    @NotNull
    @Min(1)
    float height;
    @NotNull
    ParcelType parcelType;

}
