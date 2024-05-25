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

import com.fsdm.pfe.delix.entity.City;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link City}
 */
@Value
public class CityRequestDto implements Serializable {
    Long id;
    String code;
    String name;
    String countryCode;
    String postalCode;
    ProvinceRequestDto provinceCode;
    Date createdAt;
    Date updatedAt;
}