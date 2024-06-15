
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 20:10
 *  * @modified : 29/04/2024, 20:10
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.model.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.User}
 */
@Value
public class UserRequestDto implements Serializable {
    Long id;
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