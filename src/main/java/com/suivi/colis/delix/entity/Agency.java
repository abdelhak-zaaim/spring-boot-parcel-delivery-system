
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:24
 *  * @modified : 23/04/2024, 18:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suivi.colis.delix.model.MapsLocationPoint;
import com.suivi.colis.delix.util.Constants;
import com.suivi.colis.delix.validation.location.ValidMapsLocationPoint;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity

public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String agencyCode;

    @Column(nullable = false)
    private String agencyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address agencyAddress;

    private Date agencyEstablishedDate;

    @ManyToOne
    private Admin createdBy;

    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    private String agencyContactNumber;

    @Email
    @NotNull
    private String agencyEmail;

    @OneToMany(mappedBy = "associatedAgency")
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<AgencyEmployee> agencyEmployees;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdateDate;

    @ValidMapsLocationPoint
    private MapsLocationPoint locationPoint;

    public Agency(String agencyCode, String agencyName, Address agencyAddress, Admin createdBy) {
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.createdBy = createdBy;
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
