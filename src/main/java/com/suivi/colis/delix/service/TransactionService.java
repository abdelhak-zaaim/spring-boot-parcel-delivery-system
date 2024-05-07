/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:56
 *  * @modified : 01/05/2024, 18:56
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service;

import com.suivi.colis.delix.entity.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {
    Transaction loadTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> makeMultipleTransactions(List<Transaction> transactions);
}
