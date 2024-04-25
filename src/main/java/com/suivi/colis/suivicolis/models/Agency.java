/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:24
 *  * @modified : 23/04/2024, 18:24
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.utils.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String agencyCode;
    private String agencyName;
    @OneToOne
    private Address agencyAddress;

    private Date agencyEstablishedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    private Admin createdBy;

    private String agencyContactNumber;
    private String agencyEmail;

    @OneToMany(mappedBy = "associatedAgency")
    private Set<AgencyEmployee> agencyEmployees;


    private String agencyManagerContact;


}
