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

import com.fsdm.pfe.delix.entity.location.Area;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.location.Area}
 */
@Value
public class AreaResponseDto implements Serializable {
    Long id;
    String code;
    String name;

    String cityCode;
    String postcode;
    Date createdAt;
    Date updatedAt;


    public AreaResponseDto(Area area) {
        this.id = area.getId();
        this.code = area.getCode();
        this.name = area.getName();
        this.cityCode = area.getCityCode().getCode();
        this.postcode = area.getPostCode();
        this.createdAt = area.getCreatedAt();
        this.updatedAt = area.getUpdatedAt();
    }
}