package com.sda.bankingApp.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Accounts")
@Table(name = "accounts")
public class Accounts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long accountsId;

    @Column (name = "friendly_name")
    private String friendlyName;

    @Column (name = "iban")
    private String iban;

    @Column (name = "balance")
    private Double balance;

    @Column (name = "currency")
    @Enumerated (EnumType.STRING)
    private AccountCurrencyEnum accountCurrencyEnum;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "acc_id")
    private List<Transactions> transactions = new ArrayList<>();


    public Accounts() {
    }

    public Accounts(String friendlyName, String iban, Double balance) {
        this.friendlyName = friendlyName;
        this.iban = iban;
        this.balance = balance;
    }

    public Accounts(String friendlyName, String iban, Double balance, AccountCurrencyEnum accountCurrencyEnum) {
        this.friendlyName = friendlyName;
        this.iban = iban;
        this.balance = balance;
        this.accountCurrencyEnum = accountCurrencyEnum;
    }

    public Long getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Long accountsId) {
        this.accountsId = accountsId;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountCurrencyEnum getAccountCurrencyEnum() {
        return accountCurrencyEnum;
    }

    public void setAccountCurrencyEnum(AccountCurrencyEnum accountCurrencyEnum) {
        this.accountCurrencyEnum = accountCurrencyEnum;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "AccountsId=" + accountsId +
                ", friendlyName='" + friendlyName + '\'' +
                ", iban=" + iban +
                ", balance=" + balance +
                ", currency=" + accountCurrencyEnum +
                '}';
    }
}
