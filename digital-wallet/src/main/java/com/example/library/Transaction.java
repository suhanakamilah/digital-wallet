package com.example.library;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private UUID transaction_id;
    @Enumerated(EnumType.STRING)
    private TransactionType type; // (CREDIT, DEBIT, TRANSFER)
    private BigDecimal amount;
    @Column(name = "source_user")
    private UUID sourceUser;
    @Column(name = "destination_user")
    private UUID destinationUser;
    private ZonedDateTime created_time;


    public UUID getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(UUID transaction_id) {this.transaction_id =transaction_id;}
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public UUID getSourceUser() {
        return sourceUser;
    }
    public void setSourceUser(UUID sourceUser) {
        this.sourceUser = sourceUser;
    }
    public UUID getDestinationUser() {
        return destinationUser;
    }
    public void setDestinationUser(UUID destinationUser) {
        this.destinationUser = destinationUser;
    }
    public ZonedDateTime getCreated_time() { return created_time;}
    public void setCreated_time(ZonedDateTime created_time) {
        this.created_time = created_time;
    }

}
