package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.CustomerDao;
import com.sda.bankingApp.config.HibernateUtil;

import java.security.SecureRandom;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//
//        Register register = new Register();
//
//        register.register();

//        LogIn logIn = new LogIn();
//        Customer c = logIn.loginCheck();

        LogInSecond logInSecond = new LogInSecond();
        Customer c = logInSecond.loginCheck();

//        LogInThird logInThird = new LogInThird();
//        Customer c = logInThird.loginCheck();
        DebitAccount debitAccount = new DebitAccount();
        debitAccount.createDebitAccount(c);




//

//        HibernateUtil.getSessionFactory();
    }
}
