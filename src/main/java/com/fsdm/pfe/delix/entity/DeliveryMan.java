
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:17
 *  * @modified : 23/04/2024, 19:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.MapsLocationPoint;
import com.fsdm.pfe.delix.model.enums.Role;
import com.fsdm.pfe.delix.model.enums.UserStatus;
import com.fsdm.pfe.delix.validation.location.ValidMapsLocationPoint;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@DiscriminatorValue(Role.DELIVERY_MAN_ROLE)
public class DeliveryMan extends VehicleOperatorEmployee {


    @ValidMapsLocationPoint
    private MapsLocationPoint locationPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryArea deliveryArea;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agency associatedAgency;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + this.getRole()));
        return authorities;
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
        return this.getStatus() != null && (this.getStatus().equals(UserStatus.ACTIVE) || this.getStatus().equals(UserStatus.EMAIL_NOT_VERIFIED));
    }
}
