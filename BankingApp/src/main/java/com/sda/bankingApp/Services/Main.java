package com.sda.bankingApp.Services;

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
//
//        LogIn logIn = new LogIn();
//        logIn.loginCheck();
//
        LogInSecond logInSecond = new LogInSecond();
        logInSecond.loginCheck();

        DebitAccount debitAccount = new DebitAccount();
        debitAccount.createDebitAccount();

//        CustomerDao customerDao = new CustomerDao();
//        System.out.println(customerDao.findByUsername("crina").getCustomerId());
//        HibernateUtil.getSessionFactory();
    }
}
