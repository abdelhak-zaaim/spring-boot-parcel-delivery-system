/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:53
 *  * @modified : 26/04/2024, 00:58
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.fsdm.pfe.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsdm.pfe.delix.dto.response.UserResponseDto;
import com.fsdm.pfe.delix.model.enums.Role;
import com.fsdm.pfe.delix.model.enums.UserStatus;
import com.fsdm.pfe.delix.util.Constants;
import com.fsdm.pfe.delix.validation.user.UserValidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Table(name = "user", indexes = {
        @Index(name = "index_email", columnList = "email"),
        @Index(name = "index_id", columnList = "id")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorColumn(name = Role.USER_ROLE_NAME, discriminatorType = DiscriminatorType.STRING)
@UserValidate
public class User {
    protected final static String ROLE_PREFIX = "ROLE_";

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    FirebaseUser firebaseUser;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    Collection<Notification> notifications;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @Email
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true, updatable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private ZonedDateTime registeredAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ZonedDateTime lastUpdateDate;

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


    private Date verifiedAt;


    @PrePersist
    protected void onCreate() {
        this.registeredAt = ZonedDateTime.now(ZoneId.systemDefault());
        this.lastUpdateDate = ZonedDateTime.now(ZoneId.systemDefault());
    }
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = ZonedDateTime.now(ZoneId.systemDefault());
    }

    public boolean isEmailVerified() {
        return this.getStatus().equals(UserStatus.ACTIVE);
    }

    public UserResponseDto toUserResponseDto() {

        return new UserResponseDto(this.getId(),
                this.getFirstName(),
                this.getEmail(),
                this.getRole(),
                this.getPhoneNumber(),
                this.getAddress() != null ? this.getAddress().toAddressResponseDto() : null,
                this.getStatus(),
                this.getCin(),
                this.getDateOfBirth(),
                this.getImage()
        );
    }

    public void addNotification(Notification notification) {
        if (notifications == null) {
            notifications = new ArrayList<>();
        }
        notifications.add(notification);
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) object;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}