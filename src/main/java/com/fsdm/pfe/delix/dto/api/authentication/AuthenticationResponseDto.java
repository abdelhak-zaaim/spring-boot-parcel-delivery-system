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

import com.fsdm.pfe.delix.model.enums.Role;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Value
@Builder
public class AuthenticationResponseDto {
     String token;
     Collection<? extends GrantedAuthority> role;

}
