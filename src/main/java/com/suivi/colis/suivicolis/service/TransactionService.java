/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:56
 *  * @modified : 01/05/2024, 18:56
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.Transaction;

import java.util.List;

public interface TransactionService {
    void deleteTransaction(Long id);
    Transaction loadTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> makeMultipleTransactions(List<Transaction> transactions);
}
