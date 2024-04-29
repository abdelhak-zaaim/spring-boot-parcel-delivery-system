/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 19:23
 *  * @modified : 26/04/2024, 17:02
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.dto;

import java.util.Date;
public record ErrorDto(
        String message,
        int statusCode,
        Date timestamp,
        String path
) {
}
