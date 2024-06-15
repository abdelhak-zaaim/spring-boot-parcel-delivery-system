/*
 *
 *  * @project : DeliX
 *  * @created : 12/05/2024, 20:08
 *  * @modified : 12/05/2024, 20:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.DeliveryAddress}
 */
@Value
public class DeliveryAddressRequestDto implements Serializable {
    Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    String area;

    @NotNull
    @NotEmpty
    String address;

    @NotNull
    @NotEmpty
    String contactFirstName;

    @NotEmpty
    @NotNull
    String contactLastName;

    @NotNull

    @NotBlank
    @NotEmpty
    String contactNumber;

    @NotNull
    @NotEmpty
    @Email
    String contactEmail;
}