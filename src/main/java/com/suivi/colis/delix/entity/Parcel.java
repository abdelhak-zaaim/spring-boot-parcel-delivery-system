/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 17:52
 *  * @modified : 23/04/2024, 17:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suivi.colis.delix.model.enums.ParcelStatus;
import com.suivi.colis.delix.model.enums.ParcelType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdateDate;

    private Date estimatedDeliveryDate;
    private Date deleveryDate;


    @OneToMany(fetch = FetchType.LAZY)
    private List<ParcelHistory> parcelHistories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer senderCustomer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private DeliveryAddress pickupAddress;

    @ManyToOne(fetch = FetchType.LAZY)

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private DeliveryAddress receiverAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Agency departedAgency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
