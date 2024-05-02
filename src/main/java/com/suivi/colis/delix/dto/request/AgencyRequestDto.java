
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:57
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.dto.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Agency}
 */
@Value
public class AgencyRequestDto implements Serializable {
    String agencyCode;
    @NotBlank
    @NotNull
    String agencyName;
    @NotNull
    AddressRequestDto agencyAddress;
    Date agencyEstablishedDate;
    String agencyContactNumber;
    @NotNull
    String agencyEmail;
    Set<AgencyEmployeeDto> agencyEmployees;
    Date creationDate;
    MapsLocationPointRequestDto locationPoint;
}