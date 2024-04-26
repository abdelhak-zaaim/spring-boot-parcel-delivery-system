/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 26/04/2024, 01:06
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:24
 *  * @modified : 23/04/2024, 18:24
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.entities;

import com.suivi.colis.suivicolis.utils.helpers.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private String agencyContactNumber;
    private String agencyEmail;
    @OneToMany(mappedBy = "associatedAgency")
    private Set<AgencyEmployee> agencyEmployees;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public Agency(String agencyCode, String agencyName, Address agencyAddress, Admin createdBy) {
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.createdBy = createdBy;
    }

    @PrePersist
    protected void onCreated() {
        Date date = DateUtils.getCurrentDateWithSpecifiedTimeZone();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = DateUtils.getCurrentDateWithSpecifiedTimeZone();
    }

}
