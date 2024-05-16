/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 16:27
 *  * @modified : 16/05/2024, 16:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.api.authentication;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationResponseDto {
     String accessToken;



    public AuthenticationResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
