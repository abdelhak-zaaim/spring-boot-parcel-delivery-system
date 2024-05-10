/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 16:51
 *  * @modified : 10/05/2024, 16:51
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.util.Constants;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;

@Getter
@Value
public class RegisterRequestDto implements Serializable {

    @NotNull
    @NotEmpty
    String firstName;

    @NotNull
    @NotEmpty
    String lastName;

    @NotNull
    @Email
    @NotEmpty
    String email;

    @NotNull
    @NotEmpty
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;

    @NotEmpty
    @NotNull
    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    String phoneNumber;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String rePassword;

}
