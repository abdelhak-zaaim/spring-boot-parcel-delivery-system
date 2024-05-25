/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 17:37
 *  * @modified : 17/05/2024, 17:37
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ApiResponseDto {
    boolean success;
    String message;
    Object data;

}
