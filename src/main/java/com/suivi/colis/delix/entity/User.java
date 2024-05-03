/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:53
 *  * @modified : 26/04/2024, 00:58
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suivi.colis.delix.model.enums.Role;
import com.suivi.colis.delix.model.enums.UserStatus;
import com.suivi.colis.delix.util.Constants;
import com.suivi.colis.delix.validation.user.UserValidate;
import com.suivi.colis.delix.validation.user.email.ValidEmail;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Table(name = "users")
@Data
@RequiredArgsConstructor
@Entity
@DiscriminatorColumn(name = Role.USER_ROLE_NAME, discriminatorType = DiscriminatorType.STRING)
@UserValidate
public class User implements UserDetails {
    private final static String ROLE_PREFIX = "ROLE_";
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ValidEmail
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = Role.USER_ROLE_NAME, insertable = false, updatable = false, nullable = false)
    private String role;

    @Column(nullable = false)
    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    private String phoneNumber;

    @ManyToOne
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private Date registeredAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdateDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private UserStatus status;

    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String token;

    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String refreshToken;

    @Pattern(message = "CIN not valid", regexp = "^[A-Z0-9]{1,20}$")
    private String cin;

    private Date dateOfBirth;
    private double balance = 0;

    @URL(message = "not valid image url")
    private String image;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<LoginLog> loginLogs;

    @ManyToOne
    private PrivilegesGroup privilegesGroup;

    @PrePersist
    protected void onCreated() {
        this.password = passwordEncoder.encode(this.password);
        Date date = new Date();
        this.registeredAt = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }




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
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.status.equals(UserStatus.EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.status.equals(UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status != null && !this.status.equals(UserStatus.DISABLED);
    }
}