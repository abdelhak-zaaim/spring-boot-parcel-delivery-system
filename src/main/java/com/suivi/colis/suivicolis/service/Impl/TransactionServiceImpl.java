
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl {
    UserServiceImpl userService;
    TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, UserServiceImpl userService) {
        this.transactionRepo = transactionRepo;
        this.userService = userService;
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void makeTransactionBetweenTowUsers(User user1, User user2, double amount) {

    }
}
