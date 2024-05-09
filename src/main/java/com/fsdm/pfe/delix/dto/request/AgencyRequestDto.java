
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:57
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.util.Constants;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Agency}
 */
@Value
public class AgencyRequestDto implements Serializable {
    Long id;
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