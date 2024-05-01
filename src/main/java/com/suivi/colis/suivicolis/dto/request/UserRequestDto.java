
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 20:10
 *  * @modified : 29/04/2024, 20:10
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.dto.request;

import com.suivi.colis.suivicolis.model.enums.UserStatus;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.suivi.colis.suivicolis.entity.User}
 */
@Value
public class UserRequestDto implements Serializable {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    String name;
    @NotNull
    @Email(message = "email not valid")
    String email;
    @Pattern(message = "not valid moroccan number", regexp = "^\\+212[5-7]\\d{8}$")
    String phoneNumber;
    @NotNull
    AddressRequestDto address;
    UserStatus status;
    @Pattern(message = "CIN not valid", regexp = "^[A-Z0-9]{1,20}$")
    String cin;
    @Future(message = "Date of perth not vaalid")
    Date dateOfBirth;
    @URL(message = "not valid image url")
    String image;
}