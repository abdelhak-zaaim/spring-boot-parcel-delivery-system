
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 30/04/2024, 19:44
 *  * @modified : 30/04/2024, 19:44
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.Transaction;
import com.suivi.colis.suivicolis.entity.User;
import com.suivi.colis.suivicolis.repository.TransactionRepo;
import com.suivi.colis.suivicolis.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    UserServiceImpl userService;
    TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, UserServiceImpl userService) {
        this.transactionRepo = transactionRepo;
        this.userService = userService;
    }


    @Override
    public void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);

    }

    @Override
    public Transaction loadTransactionById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Transaction> makeMultipleTransactions(List<Transaction> transactions) {
        return transactionRepo.saveAll(transactions);
    }
}
