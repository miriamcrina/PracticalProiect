package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.AccountCurrencyEnum;
import com.sda.bankingApp.Entities.AccountTypeEnum;
import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.AccountsDao;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class DebitAccount {
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(DebitAccount.class.getName());


    public void createDebitAccount(Customer customer) {

        Accounts accounts = new Accounts();


        boolean userSelection = false;
//        String acconutType;
//        do {
//            acconutType = scanner.nextLine().toUpperCase();
//            if ( acconutType.equals("DEBIT")) {
//                accounts.setAccountTypeEnum(AccountTypeEnum.DEBIT);
//                userSelection = true;
//            } else if (acconutType.equals("CREDIT")) {
//                accounts.setAccountTypeEnum(AccountTypeEnum.CREDIT);
//                userSelection = true;
//            } else {
//                logger.warning("Invalid selection.");
//                userSelection = false;
//            }
//        }while (!userSelection) ;

        logger.info("Choose the currency of your debit account (RON, EUR, USD)");

        String currency;
        do {
            currency = scanner.nextLine().toUpperCase();
            if (currency.equals("RON")) {
                accounts.setAccountCurrencyEnum(AccountCurrencyEnum.RON);
                userSelection = true;

            } else if (currency.equals("EUR")) {
                accounts.setAccountCurrencyEnum(AccountCurrencyEnum.EUR);
                userSelection = true;

            } else if (currency.equals("USD")) {
                accounts.setAccountCurrencyEnum(AccountCurrencyEnum.USD);
                userSelection = true;

            } else {
                    logger.warning("Invalid selection.");
                    userSelection = false;
            }
        }while (!userSelection) ;


            logger.info("Would you like to set a friendly name for your debit account?  y/n");
        String friendlyName = null;
            do {
                String friendlyNameAnswer;
                friendlyNameAnswer = scanner.nextLine().toLowerCase();
                if (friendlyNameAnswer.equals("y")) {
                   logger.info("Insert the desired name: ");
                   friendlyName = scanner.next();
                   userSelection = true;
               } else if (friendlyNameAnswer.equals("n")) {
                   friendlyName = "Debit account";
                   userSelection = true;
               } else {
                        logger.warning("Invalid selection");
                        userSelection = false;

                }
            } while (!userSelection) ;


                Random random = new SecureRandom();
                String countryCode = "RO";
                int rand1 = random.nextInt(10);
                int rand2 = random.nextInt(10);
                String bankCode = "BKPL";

                final long MAX_NUMBER = 9999999999999999L;
                final long MIN_NUMBER = 1000000000000000L;
                Long rand3 = Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER - MIN_NUMBER)).longValue()));

                StringBuilder sbIban = new StringBuilder();
                String iban = sbIban.append(countryCode).append(rand1).append(rand2).append(bankCode).append(rand3).toString();
                logger.info("The IBAN code for your account is: " + iban);

                logger.info("Your initial balance is 0.0");
                double balance = 0.0;



                AccountsDao accountsDao = new AccountsDao();

                accountsDao.create(new Accounts(friendlyName, iban, balance, AccountCurrencyEnum.valueOf(currency), AccountTypeEnum.DEBIT, customer));
                logger.info("Your debit account was created successfully!");



    }
}
