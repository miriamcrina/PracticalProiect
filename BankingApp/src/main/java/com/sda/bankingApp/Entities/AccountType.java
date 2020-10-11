package com.sda.bankingApp.Entities;


import javax.persistence.*;

@Entity(name = "AccountType")
@Table(name = "account_type")

public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_type_id")
    private Long AccountTypeId;

    @Column (name = "account_type")
    private String accountType;

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

    @Override
    public String toString() {
        return "AccountType{" +
                "AccountTypeId=" + AccountTypeId +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
