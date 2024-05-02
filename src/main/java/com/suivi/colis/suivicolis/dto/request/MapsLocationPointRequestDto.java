
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:58
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.dto.request;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.suivi.colis.suivicolis.model.MapsLocationPoint}
 */
@Value
public class MapsLocationPointRequestDto implements Serializable {
    Double latitude;
    Double longitude;
}