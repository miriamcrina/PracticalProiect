package com.sda.bankingApp.Entities;


import javax.persistence.*;

@Entity(name = "Accounts")
@Table(name = "accounts")
public class Accounts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long AccountsId;

    @Column (name = "friendly_name")
    private String friendlyName;

    @Column (name = "iban")
    private Long iban;

    @Column (name = "balance")
    private Double balance;

    public Accounts() {
    }

    public Accounts(String friendlyName, Long iban, Double balance) {
        this.friendlyName = friendlyName;
        this.iban = iban;
        this.balance = balance;
    }

    public Long getAccountsId() {
        return AccountsId;
    }

    public void setAccountsId(Long accountsId) {
        AccountsId = accountsId;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public Long getIban() {
        return iban;
    }

    public void setIban(Long iban) {
        this.iban = iban;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "AccountsId=" + AccountsId +
                ", friendlyName='" + friendlyName + '\'' +
                ", iban=" + iban +
                ", balance=" + balance +
                '}';
    }
}
