
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

import com.suivi.colis.delix.util.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;


import java.io.Serializable;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Agency}
 */
@Value
public class AgencyRequestDto implements Serializable {
    Long id;
    @NotNull
    String agencyName;
    @NotNull
    AddressRequestDto agencyAddress;

    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP, message = "Invalid phone number")
    String agencyContactNumber;
    @NotNull
    String agencyEmail;
    @NotNull
    MapsLocationPointRequestDto locationPoint;

    public AgencyRequestDto(Long id, @NotNull String agencyName, @NotNull AddressRequestDto agencyAddress,
                            @NotNull String agencyContactNumber, @NotNull String agencyEmail,
                            @NotNull MapsLocationPointRequestDto locationPoint) {
        this.id = id;
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;

        this.agencyContactNumber = convertPhoneNumber(agencyContactNumber);
        this.agencyEmail = agencyEmail;
        this.locationPoint = locationPoint;
    }

    private String convertPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("0")) {
            phoneNumber = Constants.COUNTRY_CODE + phoneNumber.substring(1);
        } else if (!phoneNumber.startsWith(Constants.COUNTRY_CODE)) {
            phoneNumber = Constants.COUNTRY_CODE + phoneNumber;
        }

        return phoneNumber;
    }


}