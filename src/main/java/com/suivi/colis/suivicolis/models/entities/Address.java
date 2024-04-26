/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:49
 *  * @modified : 26/04/2024, 01:06
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 17:57
 *  * @modified : 23/04/2024, 17:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.entities;

import com.suivi.colis.suivicolis.utils.Helper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = false , columnDefinition = "varchar(255) default 'Morocco'")
    private String country;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public Address(String street, String city, String state, String zip, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    @PrePersist
    protected void onCreated() {
        Date date = Helper.getCurrentDateWithSpecifiedTimeZone();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = Helper.getCurrentDateWithSpecifiedTimeZone();
    }
}
