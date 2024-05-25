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

    DOCUMENT("Ceci est un document"),
    CONTAINS_LIQUID("Ce colis contient du liquide"),
    CONTAINS_FRAGILE("Ce colis contient des articles fragiles"),
    CONTAINS_DANGEROUS("Ce colis contient des articles dangereux");

    private final String message;

    ParcelType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
