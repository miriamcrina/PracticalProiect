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
    private static final Logger logger = Logger.getLogger(Register.class.getName());
    LogIn  logIn= new LogIn();
//    long customerID = logIn.customer.getCustomerId();
    Customer customer = logIn.customer;


    public void createDebitAccount() {
        Accounts accounts = new Accounts();


        logger.info("Choose the type of your account (debit/ credit)");

        String acconutType = scanner.nextLine().toLowerCase();
        boolean userSelection = false;
        do {
            switch (acconutType) {

                case "debit":
                    accounts.setAccountTypeEnum(AccountTypeEnum.DEBIT);
                    userSelection = true;
                    break;
                case "credit":
                    accounts.setAccountTypeEnum(AccountTypeEnum.CREDIT);
                    userSelection = true;
                    break;
                default:
                    logger.warning("Invalid selection.");
                    userSelection = false;
                    break;
            }
        }while (!userSelection) ;

        logger.info("Choose the currency of your account (RON, EUR, USD)");

        String currency = scanner.nextLine().toUpperCase();
        do {
            switch (currency) {
                case "RON":
                    accounts.setAccountCurrencyEnum(AccountCurrencyEnum.RON);
                    userSelection = true;
                    break;
                case "EUR":
                    accounts.setAccountCurrencyEnum(AccountCurrencyEnum.EUR);
                    userSelection = true;
                    break;
                case "USD":
                    accounts.setAccountCurrencyEnum(AccountCurrencyEnum.USD);
                    userSelection = true;
                default:
                    logger.warning("Invalid selection.");
                    userSelection = false;
                    break;
            }
        }while (!userSelection) ;


            logger.info("Would you like to set a friendly name for your debit account?  y/n");
            String friendlyName = null;
            do {
                String friendlyNameAnswer = scanner.nextLine().toLowerCase();
                switch (friendlyNameAnswer) {
                    case "y":
                        logger.info("Insert the desired name: ");
                        friendlyName = scanner.next();
                        userSelection = true;
                        break;
                    case "n":
                        friendlyName = "Debit account";
                        userSelection = true;
                        break;
                    default:
                        logger.warning("Invalid selection");
                        userSelection = false;
                        break;
                }
            } while (!userSelection) ;


                Random random = new SecureRandom();
                String countryCode = "RO";
                int rand1 = random.nextInt(10);
                int rand2 = random.nextInt(10);
                String bankCode = "BKPL";

                final long MAX_NUMBER_YOU_WANT_TO_HAVE = 9999999999999999L;
                final long MIN_NUMBER_YOU_WANT_TO_HAVE = 1000000000000000L;
                Long rand3 = Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE)).longValue()));

                StringBuilder sbIban = new StringBuilder();
                String iban = sbIban.append(countryCode).append(rand1).append(rand2).append(bankCode).append(rand3).toString();
                logger.info("The IBAN code for your account is: " + iban);

                logger.info("Your initial balance is 0.0");
                double balance = 0.0;


        System.out.println(customer.getCustomerId());

                AccountsDao accountsDao = new AccountsDao();
//                accountsDao.create(new Accounts(friendlyName, iban, balance, AccountCurrencyEnum.valueOf(currency), AccountTypeEnum.valueOf(acconutType), customerID));
                logger.info("Your debit account was created successfully!");



    }
}
