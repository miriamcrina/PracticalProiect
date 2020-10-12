package com.sda.bankingApp.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "AccountType")
@Table(name = "account_type")

public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_type_id")
    private Long AccountTypeId;

    @Column (name = "account_type")
    private String accountType;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "acc_type_id")
    private List<Accounts> accounts = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "acc_type_id")
    private List<AccountCurrency> currencyList = new ArrayList<>();



    public AccountType() {
    }

    public AccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountTypeId() {
        return AccountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        AccountTypeId = accountTypeId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public List<AccountCurrency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<AccountCurrency> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "AccountTypeId=" + AccountTypeId +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
