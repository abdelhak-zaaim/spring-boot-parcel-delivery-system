/*
 * **
 *  * @project : DeliX
 *  * @created : 28/04/2024, 01:03
 *  * @modified : 28/04/2024, 01:03
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @Column(updatable = false )
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date transactionDate ;

    @PrePersist
    protected void onCreated() {
        this.transactionDate = new Date();
    }


}