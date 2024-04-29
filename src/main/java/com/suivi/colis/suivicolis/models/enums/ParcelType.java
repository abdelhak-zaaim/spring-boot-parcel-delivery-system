/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:11
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.enums;

import lombok.Getter;

@Getter
public enum ParcelType {
    DOCUMENT,
    CONTAINS_BATTER,
    CONTAINS_LIQUID,
    CONTAINS_FRAGILE,
    CONTAINS_DANGEROUS,
}
