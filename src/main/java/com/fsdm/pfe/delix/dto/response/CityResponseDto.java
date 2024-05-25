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

import com.fsdm.pfe.delix.entity.City;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link City}
 */

@Value
public class CityResponseDto implements Serializable {
    Long id;
    String code;
    String name;
    String countryCode;
    String postalCode;
    String provinceCode;


    public CityResponseDto(City city) {
        this.id = city.getId();
        this.code = city.getCode();
        this.name = city.getName();
        this.countryCode = city.getCountryCode();
        this.postalCode = city.getPostalCode();
        this.provinceCode = city.getProvinceCode().getCode();

    }

}