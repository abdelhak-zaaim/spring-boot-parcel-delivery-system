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

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdatePasswordRequestDto {
    String password;
    String newPassword;
    String confirmPassword;
}
