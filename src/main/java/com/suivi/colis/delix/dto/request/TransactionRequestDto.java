
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 20:23
 *  * @modified : 29/04/2024, 20:22
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.delix.dto.request;

import com.suivi.colis.delix.entity.Transaction;
import com.suivi.colis.delix.model.enums.TransactionType;
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
public class TransactionRequestDto implements Serializable {
    UserRequestDto user;
    @Positive
    double amount;
    @NotNull
    TransactionType transactionType;
    String description;
    @PastOrPresent
    Date transactionDate;
}