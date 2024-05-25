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

import com.fsdm.pfe.delix.entity.Province;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Province}
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