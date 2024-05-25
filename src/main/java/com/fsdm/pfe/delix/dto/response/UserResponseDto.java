/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 20:06
 *  * @modified : 08/05/2024, 20:06
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.model.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.User}
 */
@Value
@Getter
@Setter
public class UserResponseDto implements Serializable {
    Long id;
    String name;
    String email;
    String role;
    String phoneNumber;
    AddressResponseDto addressDto;
    UserStatus status;
    String cin;
    Date dateOfBirth;
    @URL(message = "not valid image url")
    String image;

    public UserResponseDto(Long id, String name, String email, String role, String phoneNumber, AddressResponseDto addressDto, UserStatus status, String cin, Date dateOfBirth, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.addressDto = addressDto;
        this.status = status;
        this.cin = cin;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }
}