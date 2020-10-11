package com.sda.bankingApp.Entities;

import javax.persistence.*;
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
    private Long AccountIdReceiver;

    @Column(name = "amount_sent")
    private Double AmountSent;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transactions() {
    }

    public Transactions(String description, Long accountIdReceiver, Double amountSent, LocalDateTime transactionDate) {
        this.description = description;
        AccountIdReceiver = accountIdReceiver;
        AmountSent = amountSent;
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
        return AccountIdReceiver;
    }

    public void setAccountIdReceiver(Long accountIdReceiver) {
        AccountIdReceiver = accountIdReceiver;
    }

    public Double getAmountSent() {
        return AmountSent;
    }

    public void setAmountSent(Double amountSent) {
        AmountSent = amountSent;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", description='" + description + '\'' +
                ", AccountIdReceiver=" + AccountIdReceiver +
                ", AmountSent=" + AmountSent +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
