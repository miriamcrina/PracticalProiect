package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
         final Logger logger = Logger.getLogger(DebitAccount.class.getName());

//        Register register = new Register();
//
//        register.register();


        LogIn logIn = new LogIn();
        Customer c = logIn.loginCheck();

        ShowMenu showMenu = new ShowMenu();
        showMenu.showMenu(c);

//        Portfolio portfolio = new Portfolio();
//        portfolio.viewPortfolio(c);

//        DebitAccount debitAccount = new DebitAccount();
//        debitAccount.createDebitAccount(c);

//         Deposit deposit = new Deposit();
//         deposit.depositMoney(c);
//
//        Transfer transfer = new Transfer();
//        transfer.transferMoney(c);

//

//        HibernateUtil.getSessionFactory();
    }
}
