package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.AccountCurrencyEnum;
import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.AccountsDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Deposit {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Deposit.class.getName());

    public void depositMoney(Customer customer) {
        AccountsDao accountsDao = new AccountsDao();

        showAccounts(customer, accountsDao);
//
        checkAndUpdateBalance(accountsDao);

    }

    private void checkAndUpdateBalance(AccountsDao accountsDao) {
        Accounts accountToBeUpdated;
        logger.info("Select account.");
        boolean input = false;

        do {
            logger.info("Type your iban");
            String iban = scanner.nextLine();
            accountToBeUpdated = accountsDao.findByIban(iban);

            if (iban.isEmpty()) {
                logger.warning("Please fill in the field");
                input= false;

            } else if (accountToBeUpdated.getIban()== null) {
                logger.warning("Account not found. Try again");
                input = false;
            } else {

                input = true;
            }

        }while(!input);


        logger.info("Amount:");
        double sumToDeposit = scanner.nextDouble();
        double newBalance = accountToBeUpdated.getBalance() + sumToDeposit;
        accountsDao.updateBalance(accountToBeUpdated.getAccountsId(), newBalance);
        logger.info("Your new balance is " + newBalance);
    }

    private void showAccounts(Customer customer, AccountsDao accountsDao) {
        List<Accounts> accounts;
        logger.info("You have the following accounts");
        accounts = accountsDao.findAllById(customer.getCustomerId());

        if (accounts == null)
            logger.info("null");
        else if (accounts.isEmpty())
            logger.info("Cannot find any accounts");
        else {
            accounts.forEach(account->logger.info(account.getFriendlyName() + " " + account.getBalance()+  " " + account.getAccountCurrencyEnum()));}
    }
}
