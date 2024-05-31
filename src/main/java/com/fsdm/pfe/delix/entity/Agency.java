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

/**
 * This class represents an Agency in the system.
 * It is an entity class that maps to a corresponding database table.
 *
 * @Entity annotation indicates that this class is a JPA entity.
 * @Table annotation provides the table that this entity will be mapped to.
 * @AllArgsConstructor, @Getter, @Setter, @ToString, and @RequiredArgsConstructor are Lombok annotations to reduce boilerplate code.
 *
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 * @see lombok
 */
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

    /**
     * The primary key of the Agency.
     * @Id annotation specifies the primary key of the entity.
     * @GeneratedValue annotation provides the generation strategy specification for the primary key. GenerationType.IDENTITY indicates that the persistence provider must assign primary keys for the entity using a database identity column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the agency.
     * @Column annotation specifies that this field should be mapped to a column in the database. The 'nullable = false' attribute ensures that the column will not have null values.
     */
    @Column(nullable = false)
    @NotNull
    private String agencyName;

    /**
     * The address of the agency.
     * @OneToOne annotation indicates that this field should be mapped to a foreign key column in the database.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address agencyAddress;

    /**
     * The established date of the agency.
     */
    private Date agencyEstablishedDate;

    /**
     * The admin who created this agency.
     * @ManyToOne annotation indicates that this field should be mapped to a foreign key column in the database.
     * @JoinColumn annotation indicates the column used for joining an entity association or element collection.
     * @see com.fsdm.pfe.delix.entity.Admin
     */
    @ManyToOne
    private Admin createdBy;

    /**
     * The contact number of the agency.
     */
    @Column(name = "agency_contact_number")
    private String agencyContactNumber;

    /**
     * The email of the agency.
     * @Column annotation specifies that this field should be mapped to a column in the database. The 'unique = true' attribute ensures that the column will have unique values.
     */
    @Email
    @NotNull
    @Column(unique = true)
    private String agencyEmail;

    /**
     * The employees associated with this agency.
     * @OneToMany annotation indicates that this field should be mapped to a foreign key column in the database.
     * mappedBy attribute is used to specify the entity attribute that owns the relationship.
     *
     * @see com.fsdm.pfe.delix.entity.AgencyEmployee
     * @see jakarta.persistence.OneToMany
     */
    @OneToMany(mappedBy = "associatedAgency", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<AgencyEmployee> agencyEmployees;

    /**
     * The creation date of the agency.
     * @Temporal annotation specifies the type of a temporal field.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private Date creationDate;

    /**
     * The last update date of the agency.
     * @Temporal annotation specifies the type of a temporal field.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdateDate;

    /**
     * The location point of the agency.
     */
    @ValidMapsLocationPoint
    private MapsLocationPoint locationPoint;

    /**
     * Constructor for creating an Agency with specific parameters.
     */
    public Agency(String agencyCode, String agencyName, Address agencyAddress, Admin createdBy) {
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.createdBy = createdBy;
    }

    /**
     * Method executed before the entity is persisted. Sets the creation and last update dates.
     */
    @PrePersist
    protected void onCreated() {
        Date date = new Date();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    /**
     * Method executed before the entity is updated. Sets the last update date.
     */
    @PreUpdate
    protected void onUpdate() {
        // Update the last update date
        this.lastUpdateDate = new Date();
    }
}
