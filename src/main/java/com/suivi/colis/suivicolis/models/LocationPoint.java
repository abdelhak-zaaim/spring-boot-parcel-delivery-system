/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:41
 *  * @modified : 26/04/2024, 01:41
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.models;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationPoint {
    private Double latitude;
    private Double longitude;


}
