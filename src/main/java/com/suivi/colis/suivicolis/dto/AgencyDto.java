/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 19:57
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.suivi.colis.suivicolis.entity.Agency}
 */
@Value
public class AgencyDto implements Serializable {
    String agencyCode;
    String agencyName;
    AddressDto agencyAddress;
    Date agencyEstablishedDate;
    String agencyContactNumber;
    String agencyEmail;
    Set<AgencyEmployeeDto> agencyEmployees;
    Date creationDate;
    MapsLocationPointDto locationPoint;
}