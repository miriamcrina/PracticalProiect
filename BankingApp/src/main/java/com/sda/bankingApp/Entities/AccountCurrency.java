package com.sda.bankingApp.Entities;

import javax.persistence.*;

@Entity(name = "AccountCurrency")
@Table(name = "account_currency")
public class AccountCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_currency_id")
    private Long accountCurrencyId;

    @Column (name = "currency")
    private String currency;


    public AccountCurrency() {
    }

    public AccountCurrency(String currency) {
        this.currency = currency;
    }

    public Long getAccountCurrencyId() {
        return accountCurrencyId;
    }

    public void setAccountCurrencyId(Long accountCurrencyId) {
        this.accountCurrencyId = accountCurrencyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }



    @Override
    public String toString() {
        return "AccountCurrency{" +
                "AccountCurrencyId=" + accountCurrencyId +
                ", currency='" + currency + '\'' +
                '}';
    }
}
