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
    private Long accountTypeId;

    @Column (name = "account_type")
    private String accountType;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "acc_type_id")
    private List<Accounts> accounts = new ArrayList<>();




    public AccountType() {
    }

    public AccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
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

    @Override
    public String toString() {
        return "AccountType{" +
                "AccountTypeId=" + accountTypeId +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
