/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 18:17
 *  * @modified : 04/05/2024, 18:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.dto.response;

import com.suivi.colis.delix.entity.Agency;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Agency}
 */
@Value
public class AgencyDto implements Serializable {
    String agencyName;
    AddressDto agencyAddress;
    String agencyContactNumber;
    MapsLocationPointDto locationPoint;


    public AgencyDto(Agency agency) {
    this.agencyName = agency.getAgencyName();
    this.agencyAddress = new AddressDto(agency.getAgencyAddress());
    this.agencyContactNumber = agency.getAgencyContactNumber();
    this.locationPoint = new MapsLocationPointDto(agency.getLocationPoint());
}
}