/*
 *
 *  * @project : DeliX
 *  * @created : 25/05/2024, 18:57
 *  * @modified : 16/05/2024, 17:40
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 17:33
 *  * @modified : 16/05/2024, 17:33
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * This class represents a FirebaseUser in the system.
 * It is an entity class that maps to a corresponding database table.
 *
 * @Entity annotation indicates that this class is a JPA entity.
 * @Data is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields.
 * @see jakarta.persistence.Entity
 * @see lombok.Data
 */
@Entity
@Data
public class FirebaseUser {

    /**
     * The primary key of the FirebaseUser.
     *
     * @Id annotation specifies the primary key of the entity.
     * @GeneratedValue annotation provides the generation strategy specification for the primary key. GenerationType.IDENTITY indicates that the persistence provider must assign primary keys for the entity using a database identity column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The messaging key of the FirebaseUser.
     *
     * @Column annotation specifies that this field should be mapped to a column in the database. The 'nullable = false' attribute ensures that the column will not have null values.
     */
    @Column(name = "messaging_key", nullable = false)
    private String messagingKey;
}