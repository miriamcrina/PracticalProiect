package com.sda.bankingApp.Services.Dashboard;

import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Entities.Transactions;
import com.sda.bankingApp.Repository.AccountsDao;
import com.sda.bankingApp.Repository.TransactionsDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Deposit {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Deposit.class.getName());

    public void depositMoney(Customer customer) {

        AccountsDao accountsDao = new AccountsDao();
        Long accountIdReceiver;

        List<Accounts> accounts = showAccounts(customer, accountsDao);
        if(accounts.isEmpty()){
            logger.warning("You do not have any accounts. Before performing this action, please make sure you have a credit/debit account");
            return;
        }

        Accounts accountToBeUpdated = getAccountToBeUpdated(accountsDao);
        accountIdReceiver = accountToBeUpdated.getAccountsId();
        LocalDate transactionDate = LocalDate.now();
        double sumToDeposit = getSumToDeposit();
        final String description = "Deposit";


        TransactionsDao transactionsDao = new TransactionsDao();
        Transactions transactions = new Transactions(description, accountIdReceiver, sumToDeposit,  transactionDate, accountToBeUpdated);
        transactionsDao.create(transactions);


        double newBalance = accountToBeUpdated.getBalance() + sumToDeposit;
        accountsDao.updateBalance(accountToBeUpdated.getAccountsId(), newBalance);
        logger.info("Your new balance is " + newBalance);


    }

    private double getSumToDeposit() {
        double sumToDeposit = 0;
        boolean isValid = false;

        while (!isValid) {
            logger.info("Insert the amount to be deposited:");
            if (scanner.hasNextDouble()) {
                sumToDeposit = scanner.nextDouble();
                isValid = true;
                if (sumToDeposit <= 0) {
                    logger.info("Invalid amount. Try again");
                    isValid = false;
                }
            } else {
                logger.info("Invalid amount. Try again");
            }
            scanner.nextLine();
        }
        return sumToDeposit;
    }

    private Accounts getAccountToBeUpdated(AccountsDao accountsDao) {
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

            } else if (accountToBeUpdated == null) {
                logger.warning("Account not found. Try again");
                input = false;
            } else {
                input = true;
            }

        }while(!input);
        return accountToBeUpdated;
    }

    private List<Accounts> showAccounts(Customer customer, AccountsDao accountsDao) {
        List<Accounts> accounts;
        logger.info("You have the following accounts");
        accounts = accountsDao.findAllById(customer.getCustomerId());

        if (accounts == null)
            logger.info("null");
        else if (accounts.isEmpty())
            logger.info("Cannot find any accounts");
        else {
            accounts.forEach(account->logger.info(account.getFriendlyName() + " " + account.getBalance()+  " " + account.getAccountCurrencyEnum()+  " " + account.getIban()));}

        return accounts;
    }

}
