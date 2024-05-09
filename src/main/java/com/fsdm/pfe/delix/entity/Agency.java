/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:24
 *  * @modified : 23/04/2024, 18:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsdm.pfe.delix.model.MapsLocationPoint;
import com.fsdm.pfe.delix.validation.location.ValidMapsLocationPoint;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "agency", indexes = {
        @Index(name = "index_id", columnList = "id")
})
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @NotNull
    private String agencyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address agencyAddress;

    private Date agencyEstablishedDate;

    @ManyToOne
    private Admin createdBy;

    @Column(name = "agency_contact_number")
    private String agencyContactNumber;

    @Email
    @NotNull
    @Column(unique = true)
    private String agencyEmail;

    @OneToMany(mappedBy = "associatedAgency", fetch = FetchType.LAZY)
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
