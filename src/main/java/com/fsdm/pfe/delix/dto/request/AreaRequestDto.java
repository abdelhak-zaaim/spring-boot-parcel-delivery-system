/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:44
 *  * @modified : 11/05/2024, 14:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.entity.Area;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Area}
 */
@Value
public class AreaRequestDto implements Serializable {
    @NotNull
    Long id;

    @NotNull
    String code;

    String name;

    @NotNull
    CityRequestDto cityCode;

    String postcode;
    Date createdAt;
    Date updatedAt;
}