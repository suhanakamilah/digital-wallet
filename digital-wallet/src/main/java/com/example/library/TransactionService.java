package com.example.library;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionContainer transactionContainer;
    private final UserContainer userContainer;

    public TransactionService(TransactionContainer transactionContainer, UserContainer userContainer) {
        this.transactionContainer = transactionContainer;
        this.userContainer = userContainer;
    }
    //Credit (top-up) user wallet
    // Debit (payment) from user wallet.
    // Transfer funds between users.


    public BigDecimal topUpWallet(Transaction request) {
        //validate data
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid amount");

        if (request.getSourceUser() == null)
            throw new IllegalArgumentException("Source User is required");

        if (userContainer.findUserById(request.getSourceUser()) == null)
            throw new IllegalArgumentException("Source User ID does not exist");

        Users sourceUser = userContainer.findUserById(request.getSourceUser());

        sourceUser.setBalance(sourceUser.getBalance().add(request.getAmount()));
        userContainer.save(sourceUser);

        request.setType(TransactionType.CREDIT);
        request.setCreated_time(ZonedDateTime.now());
        transactionContainer.save(request);

        return sourceUser.getBalance();
    }

    public BigDecimal payment (Transaction request) {
        //validate data
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid amount");

        if (request.getSourceUser() == null)
            throw new IllegalArgumentException("Source User is required");

        if (userContainer.findUserById(request.getSourceUser()) == null)
            throw new IllegalArgumentException("Source User ID does not exist");

        Users sourceUser = userContainer.findUserById(request.getSourceUser());

        if (request.getAmount().compareTo(sourceUser.getBalance()) > 0 )
            throw new RuntimeException("Insufficient balance!");

        sourceUser.setBalance(sourceUser.getBalance().subtract(request.getAmount()));
        userContainer.save(sourceUser);

        request.setType(TransactionType.DEBIT);
        request.setCreated_time(ZonedDateTime.now());
        transactionContainer.save(request);

        return sourceUser.getBalance();
    }

    public BigDecimal fundTransfer(Transaction request) {
        //validate data
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid amount");

        if (request.getSourceUser() == null)
            throw new IllegalArgumentException("Source User is required");

        if (request.getDestinationUser() == null)
            throw new IllegalArgumentException("Destination User is required");

        if (userContainer.findUserById(request.getSourceUser()) == null)
            throw new IllegalArgumentException("Source User ID does not exist");

        if (userContainer.findUserById(request.getDestinationUser()) == null)
            throw new IllegalArgumentException("Destination User ID does not exist");

        Users sourceUser = userContainer.findUserById(request.getSourceUser());
        Users destinationUser = userContainer.findUserById(request.getDestinationUser());

        if (request.getAmount().compareTo(sourceUser.getBalance()) > 0 )
            throw new RuntimeException("Insufficient balance!");

        sourceUser.setBalance(sourceUser.getBalance().subtract(request.getAmount()));
        userContainer.save(sourceUser);

        destinationUser.setBalance(sourceUser.getBalance().add(request.getAmount()));
        userContainer.save(destinationUser);

        request.setType(TransactionType.TRANSFER);
        request.setCreated_time(ZonedDateTime.now());
        transactionContainer.save(request);

        return sourceUser.getBalance();
    }

    public List<Transaction> retrieveTransactionHistory(UUID user_id) {
        return transactionContainer.findBySourceUser(user_id);
    }
}

