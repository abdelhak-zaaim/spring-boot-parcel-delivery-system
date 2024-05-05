
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
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Agency}
 */
@Value
public class AgencyRequestDto implements Serializable {

    @NotBlank
    @NotNull
    String agencyName;
    @NotNull
    AddressRequestDto agencyAddress;

    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    String agencyContactNumber;
    @NotNull
    String agencyEmail;
    @NotNull
    MapsLocationPointRequestDto locationPoint;
}