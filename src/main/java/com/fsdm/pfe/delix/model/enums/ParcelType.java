/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.model.enums;

import lombok.Getter;

@Getter
public enum ParcelType {
    DOCUMENT,
    CONTAINS_BATTER,
    CONTAINS_LIQUID,
    CONTAINS_FRAGILE,
    CONTAINS_DANGEROUS,
}
