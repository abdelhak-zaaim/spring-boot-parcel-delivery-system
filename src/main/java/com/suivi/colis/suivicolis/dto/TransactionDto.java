/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 20:23
 *  * @modified : 29/04/2024, 20:22
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.dto;

import com.suivi.colis.suivicolis.entity.Transaction;
import com.suivi.colis.suivicolis.model.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Transaction}
 */
@Value
public class TransactionDto implements Serializable {
    UserDto user;
    @Positive
    double amount;
    @NotNull
    TransactionType transactionType;
    String description;
    @PastOrPresent
    Date transactionDate;
}