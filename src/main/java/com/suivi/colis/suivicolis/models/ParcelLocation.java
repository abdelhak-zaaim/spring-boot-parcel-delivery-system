/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:25
 *  * @modified : 26/04/2024, 01:25
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.HandlerType;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelLocation {
   private HandlerType currentUserType;
   private Long userId;
}
