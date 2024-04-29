/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:16
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.model.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum UserStatus {
    ACTIVE(new SimpleGrantedAuthority("ACTIVE")),
    INACTIVE(new SimpleGrantedAuthority("INACTIVE")),
    SUSPENDED(new SimpleGrantedAuthority("SUSPENDED")),
    DELETED(new SimpleGrantedAuthority("DELETED")),
    EXPIRED(new SimpleGrantedAuthority("EXPIRED")),
    LOCKED(new SimpleGrantedAuthority("LOCKED")),
    DISABLED(new SimpleGrantedAuthority("DISABLED"));


    private final GrantedAuthority grantedAuthority;

    UserStatus(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

}