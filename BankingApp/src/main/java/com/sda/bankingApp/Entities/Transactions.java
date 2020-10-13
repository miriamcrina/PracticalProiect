package com.sda.bankingApp.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Transactions")
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "description")
    private String description;

    @Column(name = "account_id_receiver")
    private Long accountIdReceiver;

    @Column(name = "amount_sent")
    private Double amountSent;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;


    public Transactions() {
    }

    public Transactions(String description, Long accountIdReceiver, Double amountSent, LocalDate transactionDate) {
        this.description = description;
        this.accountIdReceiver = accountIdReceiver;
        this.amountSent = amountSent;
        this.transactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccountIdReceiver() {
        return accountIdReceiver;
    }

    public void setAccountIdReceiver(Long accountIdReceiver) {
        this.accountIdReceiver = accountIdReceiver;
    }

    public Double getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(Double amountSent) {
        this.amountSent = amountSent;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }


    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", description='" + description + '\'' +
                ", AccountIdReceiver=" + accountIdReceiver +
                ", AmountSent=" + amountSent +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
