/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 17:52
 *  * @modified : 23/04/2024, 17:52
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.ParcelStatus;
import com.suivi.colis.suivicolis.models.enums.ParcelType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String codeBar;

    private float height;
    private float width;
    private float weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParcelStatus status;

    @Enumerated(EnumType.STRING)
    private ParcelType Type;

    private Date creationDate;
    private Date estimatedDeliveryDate;
    private Date deleveryDate;

    @ManyToOne
    private Customer senderCustomer;


    @ManyToOne
    @JoinColumn(name = "departedAddress", referencedColumnName = "id")
    private DeliveryAddress departedAddress;

    @ManyToOne
    @JoinColumn(name = "destinationAddress", referencedColumnName = "id")
    private DeliveryAddress destinationAddress;

    @ManyToOne
    private Agency departedAgency;
    @ManyToOne
    private Agency destinationAgency;


}
