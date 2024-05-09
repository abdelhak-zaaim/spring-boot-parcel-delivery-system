/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 18:17
 *  * @modified : 04/05/2024, 18:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.entity.Agency;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Agency}
 */
@Value
public class AgencyResponseDto implements Serializable {
    Long id;
    String agencyName;
    AddressResponseDto agencyAddress;
    String agencyContactNumber;
    MapsLocationPointDto locationPoint;


    public AgencyResponseDto(Agency agency) {
        this.id = agency.getId();
        this.agencyName = agency.getAgencyName();
        this.agencyAddress = agency.getAgencyAddress().toAddressResponseDto();
        this.agencyContactNumber = agency.getAgencyContactNumber();
        this.locationPoint = new MapsLocationPointDto(agency.getLocationPoint());
    }
}