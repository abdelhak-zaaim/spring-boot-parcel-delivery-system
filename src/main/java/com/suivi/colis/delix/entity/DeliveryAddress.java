
/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 22:54
 *  * @modified : 25/04/2024, 22:54
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DeliveryAddress  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private String state;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false,columnDefinition = "varchar(255) default 'Morocco'") // for now we use morocco as default country
    private String country;

    @NotNull
    @Column(nullable = false)
    private String postalCode;

    @NotNull
    @Column(nullable = false)
    private String contactName;

    @NotNull
    @Column(nullable = false)
    private String contactNumber;

    private String contactEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdateDate;

    public DeliveryAddress(String address, String state, String city, String postalCode, String contactName, String contactNumber) {
        this.address = address;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    @PrePersist
    protected void onCreated() {
        Date date = new Date();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }


}
