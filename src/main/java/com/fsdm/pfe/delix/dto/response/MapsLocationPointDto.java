/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 18:17
 *  * @modified : 04/05/2024, 18:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.model.MapsLocationPoint;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.model.MapsLocationPoint}
 */
@Value
public class MapsLocationPointDto implements Serializable {
    Double latitude;
    Double longitude;

    public MapsLocationPointDto(MapsLocationPoint point) {
        this.latitude = point.getLatitude();
        this.longitude = point.getLongitude();
    }
}