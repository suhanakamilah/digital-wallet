package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private final TransactionService transactionService;

    public WalletController(TransactionService transactionService, TransactionContainer transactionContainer) {
        this.transactionService = transactionService;
    }
    @PostMapping(value = "/top-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> topUp (@RequestBody Transaction request) {
        final BigDecimal balance = transactionService.topUpWallet(request);
        return ResponseEntity.ok("Transaction successful! Your new balance is RM"+ balance);
    }

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> payment (@RequestBody Transaction request) {
        final BigDecimal balance = transactionService.payment(request);
        return ResponseEntity.ok("Transaction successful! Your new balance is RM" + balance);
    }

    @PostMapping(value = "/fund-transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fundTransfer (@RequestBody Transaction request) {
        final BigDecimal balance = transactionService.fundTransfer(request);
        return ResponseEntity.ok("Transaction successful! Your new balance is RM"+ balance);
    }

    @GetMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaction> transactionHistory (@RequestParam UUID user_id) {
        return transactionService.retrieveTransactionHistory(user_id);
    }
}
