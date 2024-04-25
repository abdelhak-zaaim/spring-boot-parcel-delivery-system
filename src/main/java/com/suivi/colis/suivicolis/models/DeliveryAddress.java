/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 22:54
 *  * @modified : 25/04/2024, 22:54
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {
    public DeliveryAddress(String address, String state, String city, String postalCode, String contactName, String contactNumber) {
        this.address = address;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
        this.contactName = contactName;
        this.contactNumber = contactNumber;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String state;
    private String city;
    @Column(nullable = false,columnDefinition = "varchar(255) default 'Morocco'") // for now we use morocco as default country
    private String country;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String contactName;
    @Column(nullable = false)
    private String contactNumber;
    private String contactEmail;

}
