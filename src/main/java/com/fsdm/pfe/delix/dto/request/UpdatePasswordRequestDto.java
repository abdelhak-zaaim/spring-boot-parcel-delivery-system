/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 19:17
 *  * @modified : 18/05/2024, 19:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdatePasswordRequestDto {
    @NotNull
    String password;
    @NotNull
    String newPassword;
    @NotNull
    String confirmPassword;
}
