
/*
 * **
 *  * @project : DeliX
 *  * @created : 30/04/2024, 19:44
 *  * @modified : 30/04/2024, 19:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.Transaction;
import com.suivi.colis.delix.entity.User;
import com.suivi.colis.delix.model.enums.TransactionType;
import com.suivi.colis.delix.repository.TransactionRepo;
import com.suivi.colis.delix.repository.UserRepo;
import com.suivi.colis.delix.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    UserServiceImpl userService;
    TransactionRepo transactionRepo;
    private UserRepo userRepository;

    public TransactionServiceImpl(TransactionRepo transactionRepo, UserServiceImpl userService, UserRepo userRepository) {
        this.transactionRepo = transactionRepo;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
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


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction transfer(User fromUser, User toUser, double amount, String description, TransactionType transactionType) {

        fromUser.setBalance(fromUser.getBalance() - amount);
        userRepository.save(fromUser);

        toUser.setBalance(toUser.getBalance() + amount);
        userRepository.save(toUser);

        Transaction transaction = new Transaction();
        transaction.setFromUser(fromUser);
        transaction.setToUser(toUser);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setDescription(description);

        return transactionRepo.save(transaction);
    }

}
