/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:48
 *  * @modified : 11/05/2024, 14:48
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.entity.Province;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Province}
 */
@Value
public class ProvinceResponseDto implements Serializable {
    Long id;
    @NotNull
    String code;
    String name;
    String countryCode;
    String postalCode;
    Date createdAt;
    Date updatedAt;

    public ProvinceResponseDto(Province province) {
        this.id = province.getId();
        this.code = province.getCode();
        this.name = province.getName();
        this.countryCode = province.getCountryCode();
        this.postalCode = province.getPostalCode();
        this.createdAt = province.getCreatedAt();
        this.updatedAt = province.getUpdatedAt();
    }
}