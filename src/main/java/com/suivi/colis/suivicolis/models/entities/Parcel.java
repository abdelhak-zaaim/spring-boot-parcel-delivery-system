/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:51
 *  * @modified : 26/04/2024, 00:54
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 17:52
 *  * @modified : 23/04/2024, 17:52
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.entities;

import com.suivi.colis.suivicolis.models.enums.ParcelStatus;
import com.suivi.colis.suivicolis.models.enums.ParcelType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codeBar;
    private float height;
    private float width;
    private float weight;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParcelStatus status;
    @Enumerated(EnumType.STRING)
    private ParcelType Type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    private Date estimatedDeliveryDate;
    private Date deleveryDate;

    @ManyToOne
    private Customer senderCustomer;

    @OneToOne
    private Payment payment;

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
