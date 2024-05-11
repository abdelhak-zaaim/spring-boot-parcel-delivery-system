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

import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.location.City}
 */
@Value
public class CityResponseDto implements Serializable {
    Long id;
    String code;
    String name;
    String countryCode;
    String postalCode;
    ProvinceResponseDto provinceCode;
    List<AreaDto> areas;
    Date createdAt;
    Date updatedAt;

    /**
     * DTO for {@link com.fsdm.pfe.delix.entity.location.Area}
     */
    @Value
    public static class AreaDto implements Serializable {
        Long id;
        String code;
        String name;
        String postcode;
        Date createdAt;
        Date updatedAt;
    }
}