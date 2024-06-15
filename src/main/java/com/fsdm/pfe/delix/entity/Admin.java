/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 14:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.enums.Role;
import com.fsdm.pfe.delix.model.enums.UserStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @PrePersist
    protected void onCreated() {
        super.setStatus(UserStatus.ACTIVE);
        Date date = new Date();
        this.setRegisteredAt(date);
        this.setLastUpdateDate(date);
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
