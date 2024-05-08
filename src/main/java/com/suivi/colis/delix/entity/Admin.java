

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 14:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.model.enums.Role;
import com.suivi.colis.delix.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@DiscriminatorValue(Role.ADMIN_ROLE)
public class Admin extends Employee implements UserDetails {
    private final static String ROLE_PREFIX = "ROLE_";
    private String adminEmployeeNumber;
    @ManyToOne
    private PrivilegesGroup privilegesGroup;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + this.getRole()));


        if (this.privilegesGroup != null) {
            this.privilegesGroup.getPrivileges().forEach(privilege -> {
                authorities.add(new SimpleGrantedAuthority(privilege.name()));

            });
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.getStatus().equals(UserStatus.EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.getStatus().equals(UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() != null && !this.getStatus().equals(UserStatus.DISABLED);
    }
}
