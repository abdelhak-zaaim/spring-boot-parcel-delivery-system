/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:53
 *  * @modified : 26/04/2024, 00:58
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.entities;

import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.models.enums.UserStatus;
import com.suivi.colis.suivicolis.utils.helpers.DateUtils;
import com.suivi.colis.suivicolis.validations.user.UserValidate;
import com.suivi.colis.suivicolis.validations.user.age.AgeLimit;
import com.suivi.colis.suivicolis.validations.user.email.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "users")
@Data
@Entity
@DiscriminatorColumn(name = Role.USER_ROLE_NAME, discriminatorType = DiscriminatorType.STRING)
@UserValidate
public class User {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ValidEmail
    private String email;
    private String password;

    @Column(name = Role.USER_ROLE_NAME, insertable = false, updatable = false, nullable = false)
    private String role;

    @Column(nullable = false)
    private String phoneNumber;
    @ManyToOne
    private Address address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(unique = true)
    private String token;
    @Column(unique = true)
    private String refreshToken;
    @Column(unique = true)
    private String cin;
    @AgeLimit(minimumAge = 16)
    private Date dateOfBirth;

    private double balance = 0;

    @PrePersist
    protected void onCreated() {
        this.password = passwordEncoder.encode(this.password);
        Date date = DateUtils.getCurrentDateWithSpecifiedTimeZone();
        this.registeredAt = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = DateUtils.getCurrentDateWithSpecifiedTimeZone();
    }

}