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

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.location.Province}
 */
@Value
@Setter
@Getter

public class ProvinceRequestDto implements Serializable {
    Long id;
    @NotNull
    String code;
    String name;
    String countryCode;
    String postalCode;
}