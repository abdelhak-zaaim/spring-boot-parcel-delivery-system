/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 21:25
 *  * @modified : 18/05/2024, 21:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Setter
@Getter
@Value
public class ResetPasswordRequestDto implements Serializable {
    @NotNull
    @NotEmpty
    String token;

    @NotNull
    @NotEmpty
    @Min(8)
    String password;

    @NotNull
    @NotEmpty
    @Min(8)
    String confirmPassword;

}
