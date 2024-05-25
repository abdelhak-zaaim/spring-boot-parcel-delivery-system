/*
 * **
 *  * @project : DeliX
 *  * @created : 28/04/2024, 01:03
 *  * @modified : 28/04/2024, 01:03
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.UUID;

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


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(unique = true, updatable = false)
    private String idempotencyKey;

    @Column(updatable = false)
    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false, nullable = false)
    @NotNull(message = "Transaction type cannot be null")
    @Pattern(regexp = "^(DEBIT|CREDIT)$", message = "Transaction type must be either DEBIT or CREDIT")
    private TransactionType transactionType;

    @NotNull(message = "Description cannot be null")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date transactionDate;

    @PrePersist
    protected void onCreated() {
        this.transactionDate = new Date();
        this.idempotencyKey = UUID.randomUUID().toString();

    }


}