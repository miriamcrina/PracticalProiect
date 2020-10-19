package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.AccountsDao;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Portfolio {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Portfolio.class.getName());

    public void viewPortfolio(Customer customer) {

        AccountsDao accountsDao = new AccountsDao();


        List<Accounts> accounts;
        logger.info("You have the following accounts: ");
        accounts = accountsDao.findAllById(customer.getCustomerId());

        if (accounts == null)
            logger.info("Invalid account. Please login again");
        else if (accounts.isEmpty())
            logger.info("Cannot find any accounts");
        else {
            accounts.forEach(account -> logger.info(account.getFriendlyName() + " " + account.getBalance() + " " + account.getAccountCurrencyEnum() + " " + account.getIban()));
        }
    }
}
