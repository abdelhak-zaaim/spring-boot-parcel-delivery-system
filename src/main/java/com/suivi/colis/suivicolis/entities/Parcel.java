/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 17:52
 *  * @modified : 23/04/2024, 17:52
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;

import com.suivi.colis.suivicolis.models.enums.ParcelStatus;
import com.suivi.colis.suivicolis.models.enums.ParcelType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
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

    @OneToMany
    @ToString.Exclude
    private List<ParcelHistory> parcelHistories;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Customer senderCustomer;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "departedAddress", referencedColumnName = "id")
    private DeliveryAddress pickupAddress;

    @ManyToOne
    @JoinColumn(name = "destinationAddress", referencedColumnName = "id")
    private DeliveryAddress receiverAddress;

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
