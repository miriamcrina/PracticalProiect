package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.AccountsDao;
import com.sda.bankingApp.Repository.CustomerDao;
import com.sda.bankingApp.config.HibernateUtil;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
         final Logger logger = Logger.getLogger(DebitAccount.class.getName());
//        Register register = new Register();
//
//        register.register();


        LogInSecond logInSecond = new LogInSecond();
        Customer c = logInSecond.loginCheck();

//        LogInThird logInThird = new LogInThird();
//        Customer c = logInThird.loginCheck();
//        DebitAccount debitAccount = new DebitAccount();
//        debitAccount.createDebitAccount(c);
 Deposit deposit = new Deposit();
 deposit.depositMoney(c);
//        List<Accounts> accounts = new ArrayList<>();
//        AccountsDao accountsDao = new AccountsDao();
//        accounts = accountsDao.findAllById((long) 1);
//        logger.info(accounts.toString());


//

//        HibernateUtil.getSessionFactory();
    }
}
