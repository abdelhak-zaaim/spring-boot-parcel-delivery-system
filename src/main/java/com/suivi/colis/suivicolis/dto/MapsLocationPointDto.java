/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 19:58
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.suivi.colis.suivicolis.models.MapsLocationPoint}
 */
@Value
public class MapsLocationPointDto implements Serializable {
    Double latitude;
    Double longitude;
}