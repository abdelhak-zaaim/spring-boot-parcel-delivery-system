/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:47
 *  * @modified : 26/04/2024, 17:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:49
 *  * @modified : 26/04/2024, 01:06
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 17:57
 *  * @modified : 23/04/2024, 17:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.dto.response.AddressResponseDto;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * This class represents an Address in the system.
 * It is an entity class that maps to a corresponding database table.
 *
 * @Entity annotation indicates that this class is a JPA entity.
 * @Getter, @Setter, @ToString, @RequiredArgsConstructor, and @AllArgsConstructor are Lombok annotations to reduce boilerplate code.
 *
 * @see jakarta.persistence.Entity
 * @see lombok
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Address {

    /**
     * The area of the address.
     * @ManyToOne annotation indicates that this field should be mapped to a foreign key column in the database.
     */
    @ManyToOne
    Area area;

    /**
     * The primary key of the Address.
     * @Id annotation specifies the primary key of the entity.
     * @GeneratedValue annotation provides the generation strategy specification for the primary key. GenerationType.IDENTITY indicates that the persistence provider must assign primary keys for the entity using a database identity column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The address string.
     * @Column annotation specifies that this field should be mapped to a column in the database. The 'nullable = false' attribute ensures that the column will not have null values.
     */
    @Column(nullable = false)
    @NotNull
    private String address;

    /**
     * The creation date of the address.
     * @Temporal annotation specifies the type of a temporal field.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date creationDate;

    /**
     * The last update date of the address.
     * @Temporal annotation specifies the type of a temporal field.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

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
        this.lastUpdateDate = new Date();
    }

    /**
     * Converts this Address entity to a AddressResponseDto.
     * @return a AddressResponseDto representing this Address.
     */
    public AddressResponseDto toAddressResponseDto() {
        return new AddressResponseDto(this);
    }
}