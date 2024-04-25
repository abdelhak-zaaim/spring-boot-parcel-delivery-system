/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:38
 *  * @modified : 23/04/2024, 18:38
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.enums;

public enum Privilege {
    FULL,
    READ_PARCELS_INFO,
    READ_PARCELS_STATUS,
    READ_PARCELS_HISTORY,
    READ_PARCELS_LOCATION,
    READ_PARCELS_DETAILS,
    READ_PARCELS_OWNER,
    WRITE_PARCELS_INFO,
    WRITE_PARCELS_STATUS,
    ADD_PARCELS,
    ADD_USER,
    ADD_EMPLOYEE,
    USER_MANAGEMENT, // including add, update, suspend, delete, read, and search
    TRANSPORT_MANAGEMENT,
    AGENCY_MANAGEMENT,


    // we will add the  other privileges later

}