package com.sda.bankingApp.Services;

import java.security.SecureRandom;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

//        Register register = new Register();
//
//        register.register();
//
//        LogIn logIn = new LogIn();
//        logIn.loginCheck();
//
//        LogInSecond logInSecond = new LogInSecond();
//        logInSecond.loginCheck();

        DebitAccount debitAccount = new DebitAccount();
        debitAccount.createDebitAccount();
    }
}
