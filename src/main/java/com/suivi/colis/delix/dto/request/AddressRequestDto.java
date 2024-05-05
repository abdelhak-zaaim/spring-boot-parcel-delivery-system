

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

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Address}
 */
@Value
public class AddressRequestDto implements Serializable {
    Long id;
    @NotNull
    String street;
    @NotNull
    String city;
    @NotNull
    String state;
    @NotNull
    String zip;
    @NotNull
    String country;

}