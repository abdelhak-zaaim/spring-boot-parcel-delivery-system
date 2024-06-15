
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:58
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.model.MapsLocationPoint}
 */
@Value
public class MapsLocationPointRequestDto implements Serializable {
    @NotNull
    @Min(-99)
    @Max(99)
    Double latitude;

    @NotNull
    @Min(-99)
    @Max(99)
    Double longitude;
}