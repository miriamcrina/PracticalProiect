package com.sda.bankingApp.Services.Dashboard;

import com.sda.bankingApp.Entities.AccountCurrencyEnum;
import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Entities.Transactions;
import com.sda.bankingApp.Repository.AccountsDao;
import com.sda.bankingApp.Repository.TransactionsDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Transfer {
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Transfer.class.getName());


    public void transferMoney(Customer customer) {

        AccountsDao accountsDao = new AccountsDao();
        Long accountIdReceiver;

        showAccounts(customer, accountsDao);

        Accounts senderAccount = getSenderAccount(accountsDao);
        Accounts receiverAccount = getReceiverAccount(accountsDao);
        accountIdReceiver = receiverAccount.getAccountsId();
        double sumToTransfer = getSumToTransfer(senderAccount);
        String description = getDescription();
        LocalDate transactionDate = LocalDate.now();
        double sumToTransferConversed = getSumToTransferConversed(senderAccount, receiverAccount, sumToTransfer);


        TransactionsDao transactionsDao = new TransactionsDao();
        Transactions transactions = new Transactions(description, accountIdReceiver, sumToTransfer,  transactionDate, senderAccount);
        transactionsDao.create(transactions);


        BigDecimal bd = BigDecimal.valueOf(sumToTransfer).setScale(3, RoundingMode.HALF_EVEN);
        double newBalanceForSender = senderAccount.getBalance() - bd.doubleValue();
        accountsDao.updateBalance(senderAccount.getAccountsId(), newBalanceForSender);


        bd = BigDecimal.valueOf(sumToTransferConversed).setScale(3, RoundingMode.HALF_EVEN);
        double newBalanceForReceiver = receiverAccount.getBalance() + bd.doubleValue();
        accountsDao.updateBalance(receiverAccount.getAccountsId(), newBalanceForReceiver);

        logger.info( "Your new balance is " + newBalanceForSender);

    }

    private double getSumToTransferConversed(Accounts senderAccount, Accounts receiverAccount, double sumToTransfer) {
        double sumToTransferConversed = 0.0;

        if ( senderAccount.getAccountCurrencyEnum().toString().equals(receiverAccount.getAccountCurrencyEnum().toString())){
            sumToTransferConversed = sumToTransfer;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.RON) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.EUR)){
            sumToTransferConversed = sumToTransfer/ 4.89;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.RON) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.USD)){
            sumToTransferConversed = sumToTransfer/ 4.15;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.EUR) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.RON)){
            sumToTransferConversed = sumToTransfer * 4.89;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.EUR) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.USD)){
            sumToTransferConversed = sumToTransfer * 1.18;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.USD) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.RON)){
            sumToTransferConversed = sumToTransfer * 4.15;

        } else  if ( senderAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.USD) && receiverAccount.getAccountCurrencyEnum().equals(AccountCurrencyEnum.EUR)) {
            sumToTransferConversed = sumToTransfer * 0.85;
        }
        return sumToTransferConversed;
    }

    private Accounts getReceiverAccount(AccountsDao accountsDao) {
        Accounts receiverAccount;
        String iban;
        boolean input;
        do {
            logger.info("Type receiver's iban");
            iban = scanner.nextLine();
            receiverAccount = accountsDao.findByIban(iban);

            if (iban.isEmpty()) {
                logger.warning("Please fill in the field");
                input= false;

            } else if (receiverAccount == null) {
                logger.warning("Account not found. Try again");
                input = false;
            } else {

                input = true;
            }
        } while(!input);
        return receiverAccount;
    }

    private String getDescription() {
        logger.info("Transfer description?  y/n");
        String description = null;
        boolean userInput = false;
        do {
            String descriptionAnswer;
            descriptionAnswer = scanner.nextLine().toLowerCase();
            if (descriptionAnswer.equals("y")) {
                logger.info("Insert the desired name: ");
                description = scanner.nextLine();
                userInput= true;
            } else if (descriptionAnswer.equals("n")) {
                description = "Payment to Bank Plus account";
                userInput = true;
            } else {
                logger.warning("Invalid selection");
                userInput = false;

            }
        } while (!userInput) ;
        return description;
    }

    private double getSumToTransfer(Accounts senderAccount) {
        double sumToTransfer =0;
        boolean isValid = false;
        while (!isValid) {
            logger.info("Insert the amount to be sent:");
            if (scanner.hasNextDouble()) {
                sumToTransfer = scanner.nextDouble();
                isValid = true;
                if (sumToTransfer <= 0) {
                    logger.info("Invalid amount. Try again");
                    isValid = false;
                }
                if (sumToTransfer > senderAccount.getBalance()) {
                    logger.info("Insufficient funds");
                    isValid = false;
                }
            } else {
                logger.info("Invalid amount. Try again");
            }
            scanner.nextLine();
        }
        return sumToTransfer;
    }

    private Accounts getSenderAccount(AccountsDao accountsDao) {
        Accounts senderAccount;
        logger.info("Select account.");
        boolean input = false;

        do {
            logger.info("Type your iban");
            String iban = scanner.nextLine();
            senderAccount = accountsDao.findByIban(iban);

            if (iban.isEmpty()) {
                logger.warning("Please fill in the field");
                input= false;

            } else if (senderAccount == null) {
                logger.warning("Account not found. Try again");
                input = false;

            } else {
                input = true;
            }

        }while(!input);
        return senderAccount;
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
            accounts.forEach(account->logger.info(account.getFriendlyName() + " " + account.getBalance()+  " " + account.getAccountCurrencyEnum()+  " " + account.getIban()));}
    }
}
