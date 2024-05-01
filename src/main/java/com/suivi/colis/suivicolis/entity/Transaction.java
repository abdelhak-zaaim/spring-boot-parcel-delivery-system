/*
 * **
 *  * @project : SuiviColis
 *  * @created : 28/04/2024, 01:03
 *  * @modified : 28/04/2024, 01:03
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.entity;

import com.suivi.colis.suivicolis.model.enums.TransactionType;
import com.suivi.colis.suivicolis.util.helpers.DateUtils;
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
    @Column(updatable = false , nullable = false, precision = 10, scale = 2)
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